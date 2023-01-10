package pri.hongweihao.smallspring.beans.factory.config;


import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 定义扩展接口
 * 加载完成 BeanDefinition 后，调用此接口方法
 */
public interface BeanFactoryPostProcessor {
    /**
     * 加载完成 BeanDefinition 后，调用此方法
     * @param beanFactory bean factory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
