package io.github.hongweihao.ss07.ioc.factory.registry.singleton;


import java.util.HashMap;
import java.util.Map;

import io.github.hongweihao.ss07.ioc.factory.extend.DisposableBean;


/**
 * <p>
 * 单例对象 注册接口默认实现类
 * 负责单例对象的存储和获取
 * </p>
 *
 * @date 2022/10/26 13:46
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    Map<String, Object> singletonBeanMap = new HashMap<>();

    Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }


    protected void addSingletonBean(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }

    protected void addDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public void destroySingletons() {
        /*// ConcurrentModificationException
        disposableBeanMap.forEach((beanName, disposableBean) -> {
            DisposableBean bean = disposableBeanMap.remove(beanName);
            bean.destroy();
        });*/

        Object[] keys = disposableBeanMap.keySet().toArray();
        for (Object key : keys) {
            DisposableBean bean = disposableBeanMap.remove(key);
            bean.destroy();
        }



    }
}
