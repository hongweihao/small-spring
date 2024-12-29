package io.github.hongweihao.ss07.ioc.factory;

import cn.hutool.core.bean.BeanUtil;
import io.github.hongweihao.ss07.ioc.factory.instantiation.InstantiationStrategy;
import io.github.hongweihao.ss07.ioc.factory.instantiation.InstantiationStrategyCglibImpl;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanReference;
import io.github.hongweihao.ss07.ioc.factory.registry.PropertyValue;
import io.github.hongweihao.ss07.ioc.factory.registry.PropertyValues;
import io.github.hongweihao.ss07.ioc.factory.registry.singleton.SingletonBeanRegistryImpl;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * BeanFactory 模板类
 * 定义了获取 bean 的流程
 * 1.根据 beanName 获取。如果获取到则返回，否则下一步
 * 2.获取 beanName 对应的 BeanDefinition 信息
 * 3.创建 beanName 对应的单例对象，并调用 registry 的注册方法存储
 * 4.返回单例对象
 * </p>
 *
 * @date 2022/10/26 13:45
 */
public abstract class BeanFactoryBase extends SingletonBeanRegistryImpl implements BeanFactory {
    // private final InstantiationStrategy strategy = new JDKInstantiationStrategyImpl();
    private final InstantiationStrategy strategy = new InstantiationStrategyCglibImpl();

    @Override
    public Object getBean(String name, Object... args) {
        Object singletonBean = super.getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return (T) getBean(name);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;


    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        try {
            instance = createInstance(beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize:" + beanDefinition.getBeanClass().getName(), e);
        }
        applyPropertyValue(beanName, instance, beanDefinition);
        addSingleton(beanName, instance);
        return instance;
    }

    /**
     * <p>
     * 根据参数列表，获取匹配的构造方法初始化类实例
     * </p>
     *
     * @param beanDefinition beanDefinition
     * @param args           args
     * @return java.lang.Object
     *
     * @date 2022/11/12 14:31
     */
    @SuppressWarnings("rawtypes")
    private Object createInstance(BeanDefinition beanDefinition, Object... args) {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Optional<Constructor> matchingConstructor = Arrays.stream(constructors)
                .filter(constructor -> {
                    // 通过构造方法参数列表长度和类型选择对应的构造方法
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length != args.length) {
                        return false;
                    }
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (args[i] != null && !parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                            return false;
                        }
                    }
                    return true;
                }).findFirst();

        try {
            return strategy.createBean(beanDefinition, matchingConstructor.orElse(null), args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize " + beanDefinition.getBeanClass().getName(), e);
        }
    }

    private void applyPropertyValue(String beanName, Object instance, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果需要填充的属性是 BeanFactory 管理的对象，那么递归获取
                if (value instanceof BeanReference) {
                    value = getBean(((BeanReference) value).getBeanName());
                }

                BeanUtil.setFieldValue(instance, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Failed to set properties for " + beanName);
        }
    }

}
