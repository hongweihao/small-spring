package io.github.hongweihao.ss07.ioc.context;

import io.github.hongweihao.ss07.ioc.factory.BeanException;
import io.github.hongweihao.ss07.ioc.factory.BeanFactoryImpl;
import io.github.hongweihao.ss07.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss07.ioc.resource.reader.BeanDefinitionReaderXmlImpl;

import java.util.Map;

/**
 * 应用上下文核心模板类
 * 固定刷新方法的流程
 * 实现容器接口的方法
 */
public abstract class ApplicationContextBase implements ApplicationContext {

    private BeanFactoryImpl beanFactory;

    /**
     * 定义刷新方法的流程：
     * 1.创建BeanFactory
     * 2.获取BeanFactory
     * 3.执行扩展操作，修改BeanDefinition
     * 4.注册Bean操作扩建接口 BeanPostProcessor
     * 5.提前实例化单例Bean
     */
    @Override
    public void refresh() {
        refreshBeanFactory();

        BeanFactoryImpl beanFactory = getBeanFactory();

        /*invokePostBeanFactoryProcessor(beanFactory);

        registerPostBeanProcessor(beanFactory);

        preInitializeSingletonObjects(beanFactory);*/
    }

    /*private void invokePostBeanFactoryProcessor(BeanFactoryImpl beanFactory) {
        Map<String, BeanFactoryPostProcessor> beans = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beans.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(getBeanFactory());
        }
    }*/

    /*private void registerPostBeanProcessor(BeanFactoryImpl beanFactory) {
        Map<String, BeanPostProcessor> beans = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beans.values()) {
            beanFactory.addPostBeanProcessor(beanPostProcessor);
        }
    }*/

    /*private void preInitializeSingletonObjects(BeanFactoryImpl beanFactory) {
        beanFactory.preInitializeSingletonObjects();
    }*/

    @Override
    public Object getBean(String name, Object... args) {
        BeanFactoryImpl beanFactory = getBeanFactory();
        return beanFactory.getBean(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        BeanFactoryImpl beanFactory = getBeanFactory();
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        BeanFactoryImpl beanFactory = getBeanFactory();
        return beanFactory.getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        BeanFactoryImpl beanFactory = getBeanFactory();
        return beanFactory.getBean(name, requiredType);
    }


    protected void refreshBeanFactory() {
        this.beanFactory = createBeanFactory();
        loadBeanDefinitions(this.beanFactory);
    }

    private BeanFactoryImpl createBeanFactory() {
        return new BeanFactoryImpl();
    }

    protected BeanFactoryImpl getBeanFactory() {
        return this.beanFactory;
    }

    protected void loadBeanDefinitions(BeanFactoryImpl beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(beanFactory);
        String[] configurations = getConfigurations();
        if (configurations != null) {
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }

    protected abstract String[] getConfigurations();
}
