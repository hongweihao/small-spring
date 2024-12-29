package io.github.hongweihao.ss07.ioc.resource.reader;

import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinitionRegistry;
import io.github.hongweihao.ss07.ioc.resource.loader.ResourceLoader;
import io.github.hongweihao.ss07.ioc.resource.loader.ResourceLoaderImpl;

/**
 * 实现通用的方法
 */
public abstract class BeanDefinitionReaderBase implements BeanDefinitionReader {
    protected final BeanDefinitionRegistry beanDefinitionRegistry;
    protected final ResourceLoader resourceLoader;

    protected BeanDefinitionReaderBase(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = new ResourceLoaderImpl();
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
