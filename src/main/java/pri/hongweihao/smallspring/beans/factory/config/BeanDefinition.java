package pri.hongweihao.smallspring.beans.factory.config;

import pri.hongweihao.smallspring.beans.PropertyValues;

/**
 * <p>
 * Bean定义对象
 * </p>
 *
 */
public class BeanDefinition {
    private final Class<?> beanClass;
    private final PropertyValues propertyValues;

    private String initMethodName;
    private String destroyMethodName;

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
