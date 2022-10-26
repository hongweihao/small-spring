package pri.hongweihao.smallspring.registry;

/**
 * <p>
 * 单例对象注册接口
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:45
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);
}
