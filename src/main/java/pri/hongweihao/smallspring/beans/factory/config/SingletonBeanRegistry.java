package pri.hongweihao.smallspring.beans.factory.config;

import pri.hongweihao.smallspring.beans.factory.support.DisposableBeanAdapter;

/**
 * <p>
 * 单例对象注册接口
 * </p>
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);

    void registerDisposableBean(String beanName, DisposableBeanAdapter adapter);

    void destroySingletons();
}
