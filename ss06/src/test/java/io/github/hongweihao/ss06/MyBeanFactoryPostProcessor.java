package io.github.hongweihao.ss06;

import io.github.hongweihao.ss06.ioc.factory.DefaultListableBeanFactory;
import io.github.hongweihao.ss06.ioc.factory.extend.BeanFactoryPostProcessor;

/**
 * <p>
 * MyPostBeanFactory
 * </p>
 *
 * @author Karl
 * @since 2025/2/18 17:24
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(DefaultListableBeanFactory beanFactory) {
        System.out.println("MyBeanFactoryPostProcessor.postProcessBeanFactory()");
    }
}
