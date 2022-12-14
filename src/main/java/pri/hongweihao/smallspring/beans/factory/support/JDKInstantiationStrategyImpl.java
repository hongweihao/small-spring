package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * JDK 方式初始化对象
 * </p>
 *
 * @date 2022/10/27 13:44
 */
public class JDKInstantiationStrategyImpl implements InstantiationStrategy {
    @Override
    public Object createBean(BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class beanClass = beanDefinition.getBeanClass();
        if (constructor == null) {
            return beanClass.getConstructor().newInstance();
        }
        return beanClass.getConstructor(constructor.getParameterTypes()).newInstance(args);
    }
}
