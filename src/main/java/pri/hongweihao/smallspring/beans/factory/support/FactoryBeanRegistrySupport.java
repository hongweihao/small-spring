package pri.hongweihao.smallspring.beans.factory.support;


import cn.hutool.core.bean.BeanException;
import pri.hongweihao.smallspring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    Map<String, Object> factoryBeanCache = new ConcurrentHashMap<>();


    /**
     * 仅从缓存中获取FactoryBean 对象
     */
    public Object getObjectFromCache(String beanName) {
        Object object = this.factoryBeanCache.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    /**
     * 先从缓存获取，如果没有再通过 factoryBean 对象获取
     * 返回之前先放入缓存
     */
    public Object getObject(FactoryBean<?> factoryBean, String beanName) {
        // 单例类型对象
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanCache.get(beanName);
            if (object == null) {
                object = factoryBean.getObject();
                this.factoryBeanCache.put(beanName, object != null ? object : NULL_OBJECT);
            }
            return object != null ? object : NULL_OBJECT;
        }

        // 原型类型对象
        return doGetObject(factoryBean, beanName);
    }

    /**
     * 通过 factoryBean 对象获取并返回
     * （不放入缓存：prototype 类型对象使用）
     */
    public Object doGetObject(FactoryBean<?> factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeanException("Failed to get object from factoryBean: " + beanName, e);
        }
    }

}
