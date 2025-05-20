package io.github.hongweihao.ss09.ioc.aware;

import io.github.hongweihao.ss09.ioc.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);

}
