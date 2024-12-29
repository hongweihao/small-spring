package io.github.hongweihao.ss07.ioc.context;


import io.github.hongweihao.ss07.ioc.factory.BeanFactory;

/**
 * 应用上下文顶层接口
 */
public interface ApplicationContext extends BeanFactory {
    void refresh();
}
