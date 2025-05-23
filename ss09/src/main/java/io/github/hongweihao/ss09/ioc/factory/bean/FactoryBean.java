package io.github.hongweihao.ss09.ioc.factory.bean;

public interface FactoryBean<T> {

    T getObject();

    Class<T> getObjectType();

    boolean isSingleton();

}
