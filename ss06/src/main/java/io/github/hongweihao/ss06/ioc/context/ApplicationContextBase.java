package io.github.hongweihao.ss06.ioc.context;

import io.github.hongweihao.ss06.ioc.factory.BeanException;
import io.github.hongweihao.ss06.ioc.factory.BeanFactoryDefault;

import java.util.Map;

/**
 * 应用上下文核心模板类
 * 固定刷新方法的流程
 * 实现容器接口的方法
 */
public abstract class ApplicationContextBase implements ApplicationContext {

    private BeanFactoryDefault beanFactory;

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
    }

    @Override
    public Object getBean(String name, Object... args) {
        return beanFactory.getBean(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanFactory.getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return beanFactory.getBean(name, requiredType);
    }

    protected void refreshBeanFactory() {
        this.beanFactory = createBeanFactory();
        loadBeanDefinitions(this.beanFactory);
    }

    private BeanFactoryDefault createBeanFactory() {
        return new BeanFactoryDefault();
    }

    protected abstract void loadBeanDefinitions(BeanFactoryDefault beanFactory);

    protected abstract String[] getConfigurations();
}
