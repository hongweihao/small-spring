package io.github.hongweihao.ss09.ioc.aware;

import io.github.hongweihao.ss09.ioc.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);

}
