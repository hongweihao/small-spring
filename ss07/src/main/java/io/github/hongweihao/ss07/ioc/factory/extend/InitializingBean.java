package io.github.hongweihao.ss07.ioc.factory.extend;

/**
 * 扩展接口： 自定义 bean 初始化方法，由 Bean 实现
 */
public interface InitializingBean {

    /**
     * <p>
     * afterPropertiesSet： 在属性填充后执行
     * </p>
     * @author Karl
     * @since 2025/2/18 16:58
     */
    void afterPropertiesSet();
}
