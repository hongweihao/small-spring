package io.github.hongweihao.ss06.ioc.factory;

/**
 * 扩展接口：调用初始化方法前后调用
 * 初始化方法是什么？ Spring 中的 @PostConstruct 对应的方法
 * 为什么不是实例化之前调用？
 */
public interface BeanPostProcessor {

    /**
     * 执行自定义初始化接口的方法之前调用
     *
     * @param bean     bean
     * @param beanName beanName
     * @return bean
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * <p>
     * 执行自定义初始化接口的方法之后调用
     * </p>
     *
     * @param bean     bean
     * @param beanName beanName
     * @return java.lang.Object
     * @author Karl
     * @since 2025/2/18 17:01
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
