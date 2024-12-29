package io.github.hongweihao.ss07.ioc.factory.registry.singleton;

/**
 * <p>
 * 单例对象注册接口
 * </p>
 *
 * @date 2022/10/26 13:45
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);
}
