package pri.hongweihao.smallspring.beans.factory.support;


import pri.hongweihao.smallspring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;


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

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }
}
