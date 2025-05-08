package io.github.hongweihao.ss06.ioc.factory.extend;

/**
 * 扩展接口：销毁时执行，由 Bean 实现
 * 初始化方法是什么？ Spring 中的 @PostConstruct 对应的方法
 * 为什么不是实例化之前调用？
 */
public interface DisposableBean {
    void destroy();
}
