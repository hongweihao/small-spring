package pri.hongweihao.smallspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import pri.hongweihao.smallspring.beans.BeanException;
import pri.hongweihao.smallspring.beans.PropertyValue;
import pri.hongweihao.smallspring.beans.PropertyValues;
import pri.hongweihao.smallspring.beans.factory.AutowireCapableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 实现创建并存储bean
 * </p>
 *
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    //private final InstantiationStrategy instantiationStrategy = new JDKInstantiationStrategyImpl();
    private final InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategyImpl();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        instance = createInstance(beanName, beanDefinition, args);
        applyPropertyValue(beanName, instance, beanDefinition);
        super.register(beanName, instance);
        return instance;
    }

    /**
     * <p>
     * 根据参数列表，获取匹配的构造方法初始化类实例
     * </p>
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @param args           args
     * @return java.lang.Object
     * 
     * @date 2022/11/12 14:31
     */
    private Object createInstance(String beanName, BeanDefinition beanDefinition, Object... args) {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Optional<Constructor> first = Arrays.stream(constructors)
                // 参数个数匹配
                .filter(constructor -> constructor.getParameterTypes().length == args.length)
                .findFirst();
        try {
            return instantiationStrategy.createBean(beanDefinition, first.orElse(null), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanException("Failed to initialize for " + beanName);
        }

    }


    private void applyPropertyValue(String beanName, Object instance, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果需要填充的属性是 BeanFactory 管理的对象，那么递归获取
                if (value instanceof BeanReference) {
                    value = getBean(((BeanReference) value).getBeanName());
                }

                BeanUtil.setFieldValue(instance, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Failed to set properties for " + beanName);
        }
    }
}
