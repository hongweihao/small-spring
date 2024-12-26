package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.core.io.DefaultResourceLoader;
import pri.hongweihao.smallspring.core.io.ResourceLoader;

/**
 * 实现通用的方法
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    protected final BeanDefinitionRegistry beanDefinitionRegistry;
    protected final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
