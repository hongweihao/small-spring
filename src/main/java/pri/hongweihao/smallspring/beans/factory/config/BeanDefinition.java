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

    private String scope;

    private boolean singleton = true;
    private boolean prototype = false;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = ConfigurableBeanFactory.SCOPE_SINGLETON.equals(scope);
        this.prototype = ConfigurableBeanFactory.SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
}
