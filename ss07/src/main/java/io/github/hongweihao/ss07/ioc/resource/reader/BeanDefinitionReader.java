package io.github.hongweihao.ss07.ioc.resource.reader;

import io.github.hongweihao.ss07.ioc.factory.BeanException;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinitionRegistry;
import io.github.hongweihao.ss07.ioc.resource.Resource;
import io.github.hongweihao.ss07.ioc.resource.loader.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resources) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;

    void loadBeanDefinitions(String[] locations) throws BeanException;
}
