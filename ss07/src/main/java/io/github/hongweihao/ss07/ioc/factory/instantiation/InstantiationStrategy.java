package io.github.hongweihao.ss07.ioc.factory.instantiation;


import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 初始化对象策略
 * </p>
 *
 * @date 2022/10/27 13:43
 */
public interface InstantiationStrategy {
    @SuppressWarnings("rawtypes")
    Object createBean(BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws Exception;
}
