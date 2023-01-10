package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 核心实现类。实现了 BeanFactory 接口和 BeanDefinitionRegistry 接口
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BeansException("Cannot found this bean name: " + beanName);
        }
        return beanDefinition;
    }

    @Override
    public void preInitializeSingletonObjects() {
        String[] beanDefinitionNames = getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            getBean(beanDefinitionName);
        }
    }

    @Override
    public void register(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return beanDefinitionMap.entrySet().stream()
                .filter(entry -> type.isAssignableFrom(entry.getValue().getBeanClass()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> type.cast(getBean(entry.getKey())),
                        (k1, k2) -> k1));
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
