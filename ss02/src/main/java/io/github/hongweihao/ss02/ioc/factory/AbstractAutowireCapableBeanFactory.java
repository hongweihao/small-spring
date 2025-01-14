package io.github.hongweihao.ss02.ioc.factory;


import io.github.hongweihao.ss02.ioc.factory.registry.BeanDefinition;

/**
 * <p>
 * AbstractBeanFactory的子类，主要负责创建bean实例，初始化实例
 * </p>
 *
 * @author Karl
 * @date 2025/1/14 13:16
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object instance;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            throw new BeanException("Cannot instantiation a bean: " + beanDefinition.getBeanClass().getName());
        }
        super.register(beanName, instance);
        return instance;
    }
}
