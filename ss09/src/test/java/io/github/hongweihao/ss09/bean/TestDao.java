package io.github.hongweihao.ss09.bean;

import io.github.hongweihao.ss09.ioc.aware.ApplicationContextAware;
import io.github.hongweihao.ss09.ioc.aware.BeanClassLoaderAware;
import io.github.hongweihao.ss09.ioc.aware.BeanFactoryAware;
import io.github.hongweihao.ss09.ioc.aware.BeanNameAware;
import io.github.hongweihao.ss09.ioc.context.ApplicationContext;
import io.github.hongweihao.ss09.ioc.factory.BeanFactory;

/**
 * <p>
 * TestService
 * </p>
 *
 * @date 2022/10/25 13:35
 */
public class TestDao implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

    public void test() {
        System.out.print("testDao");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("TestDao setBeanName: " + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("TestDao setBeanClassLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("TestDao setBeanFactory: " + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("TestDao setApplicationContext: " + applicationContext);
    }

}
