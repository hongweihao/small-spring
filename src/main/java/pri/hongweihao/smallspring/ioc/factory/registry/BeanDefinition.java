package pri.hongweihao.smallspring.ioc.factory.registry;

/**
 * <p>
 * Bean定义对象
 * </p>
 *
 * @date 2022/10/25 13:29
 */
public class BeanDefinition {
    private final Class<?> beanClass;

    private final PropertyValues propertyValues;

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
}
