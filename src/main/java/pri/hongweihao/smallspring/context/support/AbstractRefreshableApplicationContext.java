package pri.hongweihao.smallspring.context.support;

import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.support.DefaultListableBeanFactory;

/**
 * 模板类。定义 refreshBeanFactory 的流程
 * 1.创建BeanFactory
 * 2.加载BeanDefinition
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        this.beanFactory = createBeanFactory();
        loadBeanDefinitions(this.beanFactory);
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
