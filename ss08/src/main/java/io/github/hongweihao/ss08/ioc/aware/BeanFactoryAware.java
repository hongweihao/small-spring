package io.github.hongweihao.ss08.ioc.aware;

import io.github.hongweihao.ss08.ioc.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);

}
