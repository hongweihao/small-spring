package io.github.hongweihao.ss08.ioc.aware;

import io.github.hongweihao.ss08.ioc.context.ApplicationContext;
import io.github.hongweihao.ss08.ioc.factory.BeanException;
import io.github.hongweihao.ss08.ioc.factory.extend.BeanPostProcessor;


public class ApplicationContextBeanPostProcessor implements BeanPostProcessor {

    private ApplicationContext applicationContext;

    public ApplicationContextBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
