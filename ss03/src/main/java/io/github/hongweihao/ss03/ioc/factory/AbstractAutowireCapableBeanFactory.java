package io.github.hongweihao.ss03.ioc.factory;


import io.github.hongweihao.ss03.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss03.ioc.factory.strategy.CglibInstantiationStrategy;
import io.github.hongweihao.ss03.ioc.factory.strategy.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * AbstractBeanFactory的子类，主要负责创建bean实例，初始化实例
 * </p>
 *
 * @author Karl
 * @date 2025/1/14 13:16
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        try {
            instance = createInstance(beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize:" + beanDefinition.getBeanClass().getName(), e);
        }
        addSingletonBean(beanName, instance);
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
            return instantiationStrategy.instantiate(beanDefinition, matchingConstructor.orElse(null), args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize " + beanDefinition.getBeanClass().getName(), e);
        }
    }
}