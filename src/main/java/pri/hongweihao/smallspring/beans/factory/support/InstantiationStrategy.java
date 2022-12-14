package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 初始化对象策略
 * </p>
 *
 * @date 2022/10/27 13:43
 */
public interface InstantiationStrategy {
    Object createBean(BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws Exception;
}
