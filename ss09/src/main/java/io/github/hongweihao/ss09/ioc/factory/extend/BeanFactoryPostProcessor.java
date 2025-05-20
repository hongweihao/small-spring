package io.github.hongweihao.ss09.ioc.factory.extend;

import io.github.hongweihao.ss09.ioc.factory.DefaultListableBeanFactory;

/**
 * 扩展接口，用于操作已经注册进BeanFactory中的BeanDefinition
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(DefaultListableBeanFactory beanFactory);
}
