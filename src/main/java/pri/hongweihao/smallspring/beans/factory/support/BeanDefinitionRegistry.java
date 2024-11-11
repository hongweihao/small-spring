package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;

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
