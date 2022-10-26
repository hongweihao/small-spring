package pri.hongweihao.smallspring.factory;

import pri.hongweihao.smallspring.BeanDefinition;
import pri.hongweihao.smallspring.BeanException;

/**
 * <p>
 * 实现创建并存储bean
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:52
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object instance;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            throw new BeanException("Cannot instantiation a bean: " + beanDefinition.getBeanClass().getName());
        }
        super.register(beanName, instance);
        return instance;
    }
}
