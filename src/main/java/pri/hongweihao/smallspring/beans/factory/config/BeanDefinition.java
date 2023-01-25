package pri.hongweihao.smallspring.beans.factory.config;

import pri.hongweihao.smallspring.beans.PropertyValues;

/**
 * <p>
 * Bean定义对象
 * </p>
 */
public class BeanDefinition {
    private final Class<?> beanClass;
    private final PropertyValues propertyValues;
    private String initMethodName;
    private String destroyMethodName;

    private final String scope;

    private final boolean singleton;
    private final boolean prototype;

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues, String scope) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
        this.scope = scope;
        this.singleton = ConfigurableBeanFactory.SCOPE_SINGLETON.equals(scope);
        this.prototype = ConfigurableBeanFactory.SCOPE_PROTOTYPE.equals(scope);
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

    public String getScope() {
        return scope;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
}
