package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 单例对象 注册接口默认实现类
 * 负责单例对象的存储和获取
 * </p>
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected final Object NULL_OBJECT = new Object();

    Map<String, Object> singletonBeanMap = new HashMap<>();

    Map<String, DisposableBeanAdapter> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    @Override
    public void registerDisposableBean(String beanName, DisposableBeanAdapter adapter) {
        disposableBeanMap.put(beanName, adapter);
    }

    @Override
    public void destroySingletons() {
        for (String key : disposableBeanMap.keySet()) {
            DisposableBeanAdapter adapter = disposableBeanMap.get(key);
            try {
                adapter.destroy();
            } catch (Exception e) {
                throw new BeansException("Failed to invoke destroy method of bean " + key, e);
            }
        }
        disposableBeanMap.clear();
    }

    public void registerSingleton(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }
}
