package pri.hongweihao.smallspring.context.support;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.ListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanFactoryPostProcessor;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;
import pri.hongweihao.smallspring.context.*;
import pri.hongweihao.smallspring.context.event.ApplicationEventMulticaster;
import pri.hongweihao.smallspring.context.event.ContextClosedEvent;
import pri.hongweihao.smallspring.context.event.ContextRefreshedEvent;
import pri.hongweihao.smallspring.context.event.SimpleApplicationEventMulticaster;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文核心模板类
 * 固定刷新方法的流程
 * 实现容器接口的方法
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext, ApplicationEventPublisher {
    private final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 定义刷新方法的流程：
     * 1.创建BeanFactory
     * 2.获取BeanFactory
     * 3.注册context aware
     * 4.执行扩展操作，修改BeanDefinition
     * 5.注册Bean操作扩建接口 BeanPostProcessor
     * 6.提前实例化单例Bean
     */
    @Override
    public void refresh() {
        // 刷新BeanFactory
        refreshBeanFactory();

        // 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 注册上下文感知对象
        beanFactory.addPostBeanProcessor(new ApplicationContextAwareProcessor(this));

        // 调用BeanFactoryProcessor
        invokePostBeanFactoryProcessor(beanFactory);

        // 注册BeanProcessor
        registerPostBeanProcessor(beanFactory);

        // 预加载单例对象
        preInitializeSingletonObjects(beanFactory);

        // 初始化事件广播对象
        initApplicationEventMulticaster();

        // 注册监听器
        registerListeners();

        // 刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void registerListeners() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        Collection<ApplicationListener> listeners = beanFactory.getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : listeners) {
            applicationEventMulticaster.addListener(listener);
        }
    }

    private void initApplicationEventMulticaster() {
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        getBeanFactory().registerSingleton(this.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokePostBeanFactoryProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beans = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beans.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(getBeanFactory());
        }
    }

    private void registerPostBeanProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beans = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beans.values()) {
            beanFactory.addPostBeanProcessor(beanPostProcessor);
        }
    }

    private void preInitializeSingletonObjects(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.preInitializeSingletonObjects();
    }

    @Override
    public Object getBean(String name, Object... args) {
        ListableBeanFactory beanFactory = getBeanFactory();
        return beanFactory.getBean(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        ListableBeanFactory beanFactory = getBeanFactory();
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        ListableBeanFactory beanFactory = getBeanFactory();
        return beanFactory.getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        ListableBeanFactory beanFactory = getBeanFactory();
        return beanFactory.getBean(name, requiredType);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.applicationEventMulticaster.multicastEvent(event);
    }
}
