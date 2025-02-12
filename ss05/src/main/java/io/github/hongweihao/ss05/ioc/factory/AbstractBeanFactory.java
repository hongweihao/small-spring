package io.github.hongweihao.ss05.ioc.factory;


import io.github.hongweihao.ss05.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss05.ioc.factory.registry.singleton.DefaultSingletonBeanRegistry;

import java.util.Objects;

/**
 * <p>
 * BeanFactory 模板类
 * 定义了获取 bean 的流程：
 * 1.先从单例池中获取，如果存在则直接返回
 * 2.如果不存在，则从 beanDefinition 中获取对应的类信息
 * 3.根据类信息创建对象并返回
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:45
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName, Object... args) {
        Object singletonBean = super.getSingletonBean(beanName);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
