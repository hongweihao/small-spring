package io.github.hongweihao.ss06.ioc.factory;

/**
 * 扩展接口：调用初始化方法前后调用
 * 初始化方法是什么？ Spring 中的 @PostConstruct 对应的方法
 * 为什么不是实例化之前调用？
 */
public interface BeanPostProcessor {

    /**
     * 初始化之前调用
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
