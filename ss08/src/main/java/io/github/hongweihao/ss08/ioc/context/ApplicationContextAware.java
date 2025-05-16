package io.github.hongweihao.ss08.ioc.context;

import io.github.hongweihao.ss08.ioc.aware.ApplicationContextAware;

public class ApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
