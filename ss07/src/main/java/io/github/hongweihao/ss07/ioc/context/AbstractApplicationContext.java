package io.github.hongweihao.ss07.ioc.context;

import java.util.Map;

import io.github.hongweihao.ss07.ioc.factory.DefaultListableBeanFactory;
import io.github.hongweihao.ss07.ioc.factory.extend.BeanFactoryPostProcessor;
import io.github.hongweihao.ss07.ioc.factory.extend.BeanPostProcessor;

/**
 * <p>
 * 抽象类，固定refresh方法的流程
 * </p>
 *
 * @since 2025/05/13 16:16
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /**
     * <p>
     * 刷新上下文
     * </p>
     * 
     * @since 2025/05/13 16:15
     */
    public void refresh() {
        // 1. 创建BeanFactory
        refreshBeanFactory();

        // 2. 获取BeanFactory
        DefaultListableBeanFactory beanFactory = getBeanFactory();

        // 3. 调用 BeanFactoryPostProcessor 列表
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. 注册 BeanPostProcessor 列表
        registerBeanPostProcessors(beanFactory);

        // 5. 触发单例对象创建（提前初始化）
        beanFactory.preInstantiateSingletons();
    }

    private void refreshBeanFactory() {
        // 创建BeanFactory
        this.beanFactory = new DefaultListableBeanFactory();
        // 加载BeanDefinitions
        loadBeanDefinitions();
    }

    protected abstract void loadBeanDefinitions();

    protected DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected void invokeBeanFactoryPostProcessors(DefaultListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        beanMap.forEach((beanName, beanFactoryPostProcessor) -> beanFactoryPostProcessor.postProcessBeanFactory(beanFactory));
    }

    protected void registerBeanPostProcessors(DefaultListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        beanMap.forEach((beanName, beanPostProcessor) -> beanFactory.addBeanPostProcessor(beanPostProcessor));
    }

    @Override
    public Object getBean(String name, Object... args) {
        return beanFactory.getBean(name, args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        beanFactory.destroySingletons();
    }
}
