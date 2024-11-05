package pri.hongweihao.smallspring.factory.support;

import pri.hongweihao.smallspring.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.BeanException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 核心实现类。实现了 BeanFactory 接口和 BeanDefinitionRegistry 接口
 *
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:57
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BeanException("Cannot found this bean name: " + beanName);
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
