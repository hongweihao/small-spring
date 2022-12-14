package pri.hongweihao.smallspring.beans.factory.config;

/**
 * <p>
 * 单例对象注册接口
 * </p>
 *
 * @date 2022/10/26 13:45
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);
}
