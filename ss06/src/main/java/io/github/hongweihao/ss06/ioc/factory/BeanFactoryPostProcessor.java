package io.github.hongweihao.ss06.ioc.factory;


/**
 * 扩展接口，用于操作已经注册进BeanFactory中的BeanDefinition
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(BeanFactoryDefault beanFactory);
}
