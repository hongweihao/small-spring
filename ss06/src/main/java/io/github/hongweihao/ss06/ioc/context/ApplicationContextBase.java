package pri.hongweihao.smallspring.context.support;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.ListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanFactoryPostProcessor;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;
import pri.hongweihao.smallspring.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * 应用上下文核心模板类
 * 固定刷新方法的流程
 * 实现容器接口的方法
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

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

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        invokePostBeanFactoryProcessor(beanFactory);

        registerPostBeanProcessor(beanFactory);

        preInitializeSingletonObjects(beanFactory);
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
}
