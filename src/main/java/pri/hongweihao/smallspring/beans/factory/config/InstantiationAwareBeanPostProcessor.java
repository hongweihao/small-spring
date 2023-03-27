package pri.hongweihao.smallspring.beans.factory.config;

/**
 * <p>
 * 初始化对象前执行
 * </p>
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName);
}
