package io.github.hongweihao.ss07.ioc.factory;


import cn.hutool.core.bean.BeanUtil;
import io.github.hongweihao.ss07.ioc.factory.extend.BeanPostProcessor;
import io.github.hongweihao.ss07.ioc.factory.extend.DisposableBean;
import io.github.hongweihao.ss07.ioc.factory.extend.InitializingBean;
import io.github.hongweihao.ss07.ioc.factory.instantiation.CglibInstantiationStrategy;
import io.github.hongweihao.ss07.ioc.factory.instantiation.InstantiationStrategy;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanReference;
import io.github.hongweihao.ss07.ioc.factory.registry.PropertyValue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        try {
            instance = createInstance(beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize:" + beanDefinition.getBeanClass().getName(), e);
        }
        applyPropertyValues(beanName, instance, beanDefinition);
        instance = initializeBean(beanName, instance, beanDefinition);

        addSingletonBean(beanName, instance);

        registerDisposableBeanIfNecessary(beanName, instance);

        return instance;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object instance) {
        if (instance instanceof DisposableBean) {
            addDisposableBean(beanName, (DisposableBean) instance);
        }
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

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 实现属性填充逻辑
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // 这是hutool工具类的方法
                BeanUtil.setFieldValue(bean, propertyValue.getName(), value);
            }
        } catch (Exception e) {
            throw new BeanException("Failed to set property values:" + beanName, e);
        }
    }

    private Object initializeBean(String beanName, Object instance, BeanDefinition beanDefinition) {
        instance = applyBeanPostProcessorsBeforeInitialization(beanName, instance);
        invokeInitMethods(beanName, instance, beanDefinition);
        return applyBeanPostProcessorsAfterInitialization(beanName, instance);
    }

    private Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object instance) {
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            if (current == null) return instance;
            instance = current;
        }
        return instance;
    }

    private Object applyBeanPostProcessorsAfterInitialization(String beanName, Object instance) {
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            if (current == null) return instance;
            instance = current;
        }
        return instance;
    }

    private Object invokeInitMethods(String beanName, Object instance, BeanDefinition beanDefinition) {
        if (instance instanceof InitializingBean) {
            try {
                ((InitializingBean) instance).afterPropertiesSet();
            } catch (Exception e) {
                throw new BeanException("Failed to invoke afterPropertiesSet method on bean with name " + beanName, e);
            }
        }
        return instance;
    }


    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.add(beanPostProcessor);
    }
}
