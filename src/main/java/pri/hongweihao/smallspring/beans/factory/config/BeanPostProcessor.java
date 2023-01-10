package pri.hongweihao.smallspring.beans.factory.config;

/**
 * 扩展对象，在创建 bean 对象成功之后。
 * 执行特定初始化方法前后，调用此接口的方法
 */
public interface BeanPostProcessor {
    /**
     * 执行初始化方法前调用
     * @param bean bean 对象
     * @param beanName bean 对象名
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 执行初始化方法后调用
     * @param bean bean 对象
     * @param beanName bean 对象名
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
