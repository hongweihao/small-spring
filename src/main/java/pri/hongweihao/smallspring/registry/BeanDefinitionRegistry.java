package pri.hongweihao.smallspring.registry;

import pri.hongweihao.smallspring.BeanDefinition;

/**
 * <p>
 * BeanDefinition 注册接口
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:59
 */
public interface BeanDefinitionRegistry {
    void register(String beanName, BeanDefinition beanDefinition);
}
