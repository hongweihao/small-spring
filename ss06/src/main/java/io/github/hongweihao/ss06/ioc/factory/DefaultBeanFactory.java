package io.github.hongweihao.ss06.ioc.factory;

import io.github.hongweihao.ss06.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss06.ioc.factory.registry.BeanDefinitionRegistry;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 核心实现类。实现了 BeanFactory 接口和 BeanDefinitionRegistry 接口
 * </p>
 *
 * @date 2022/10/26 13:57
 */
public class DefaultBeanFactory extends BeanFactoryBase implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
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

    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return beanDefinitionMap.entrySet().stream()
                .filter(entry -> type.isAssignableFrom(entry.getValue().getBeanClass()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> type.cast(getBean(entry.getKey())),
                        (k1, k2) -> k1));
    }


    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
