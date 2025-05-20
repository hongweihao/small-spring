package io.github.hongweihao.ss09.ioc.context;

import io.github.hongweihao.ss09.ioc.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {
    void refresh();

    void registerShutdownHook();

    void close();
}
