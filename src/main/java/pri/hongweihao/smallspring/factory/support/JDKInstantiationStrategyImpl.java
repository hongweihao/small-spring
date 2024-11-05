package pri.hongweihao.smallspring.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import pri.hongweihao.smallspring.factory.config.BeanDefinition;

/**
 * <p>
 * JDK 方式初始化对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/27 13:44
 */
public class JDKInstantiationStrategyImpl implements InstantiationStrategy {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object createBean(BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class beanClass = beanDefinition.getBeanClass();
        if (constructor == null) {
            return beanClass.getConstructor().newInstance();
        }
        return beanClass.getConstructor(constructor.getParameterTypes()).newInstance(args);
    }
}