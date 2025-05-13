package io.github.hongweihao.ss07.ioc.factory;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss07.ioc.factory.registry.BeanDefinitionRegistry;

/**
 * <p>
 * 核心实现类，实现了 BeanFactory 接口和 BeanDefinitionRegistry 接口
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

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                map.put(beanName, (T) getBean(beanName));
            }
        });
        return map;
    }

    public void preInstantiateSingletons() {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}
