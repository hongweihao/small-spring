package io.github.hongweihao.ss04.ioc.factory;


import io.github.hongweihao.ss04.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss04.ioc.factory.registry.SingletonBeanRegistryDefault;
import io.github.hongweihao.ss04.ioc.factory.strategy.InstantiationStrategy;
import io.github.hongweihao.ss04.ioc.factory.strategy.InstantiationStrategyCglib;

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
 * @author Karl
 * @date 2022/10/26 13:45
 */
public abstract class BeanFactoryBase extends SingletonBeanRegistryDefault implements BeanFactory {
    // private final InstantiationStrategy strategy = new
    // JDKInstantiationStrategyImpl();
    private final InstantiationStrategy strategy = new InstantiationStrategyCglib();


    @Override
    public Object getBean(String name, Object... args) {
        Object singletonBean = super.getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        try {
            instance = createInstance(beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize:" + beanDefinition.getBeanClass().getName(), e);
        }
        register(beanName, instance);
        return instance;
    }

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

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
