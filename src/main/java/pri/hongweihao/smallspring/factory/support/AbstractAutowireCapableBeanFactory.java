package pri.hongweihao.smallspring.factory.support;

import cn.hutool.core.bean.BeanUtil;
import pri.hongweihao.smallspring.BeanException;
import pri.hongweihao.smallspring.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.factory.config.BeanReference;
import pri.hongweihao.smallspring.factory.config.PropertyValue;
import pri.hongweihao.smallspring.factory.config.PropertyValues;


import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 实现创建并存储bean
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:52
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    // private final InstantiationStrategy strategy = new JDKInstantiationStrategyImpl();
    private final InstantiationStrategy strategy = new CglibInstantiationStrategyImpl();

    @Override
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
     * @author Karl
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