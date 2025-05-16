package io.github.hongweihao.ss08.ioc.factory.registry;

/**
 * <p>
 * BeanDefinition 注册接口
 * </p>
 *
 * @date 2022/10/26 13:59
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
