package io.github.hongweihao.ss06;

import io.github.hongweihao.ss06.ioc.factory.extend.BeanPostProcessor;

/**
 * <p>
 * MyPostBeanFactory
 * </p>
 *
 * @author Karl
 * @since 2025/2/18 17:24
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(beanName + " -> MyBeanPostProcessor.postProcessBeforeInitialization()");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println(beanName + " -> MyBeanPostProcessor.postProcessAfterInitialization()");
        return bean;
    }
}
