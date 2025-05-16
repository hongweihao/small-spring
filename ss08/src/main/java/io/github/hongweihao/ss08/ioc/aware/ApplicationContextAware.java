package io.github.hongweihao.ss08.ioc.aware;

import io.github.hongweihao.ss08.ioc.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);

}
