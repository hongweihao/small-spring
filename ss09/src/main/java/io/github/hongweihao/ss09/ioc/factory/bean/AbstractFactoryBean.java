package io.github.hongweihao.ss09.ioc.factory.bean;

import io.github.hongweihao.ss09.ioc.factory.registry.singleton.DefaultSingletonBeanRegistry;

public abstract class AbstractFactoryBean<T> extends DefaultSingletonBeanRegistry implements FactoryBean<T> {


    

    @Override
    public T getObject() {
        return null;
    }

    @Override
    public Class<T> getObjectType() {
        return null;
    }


}
