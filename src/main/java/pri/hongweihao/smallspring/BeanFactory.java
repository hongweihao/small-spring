package pri.hongweihao.smallspring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Bean 工厂对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:29
 */
public class BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void register(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    public BeanDefinition getBean(String name) {
        return beanDefinitionMap.get(name);
    }


}
