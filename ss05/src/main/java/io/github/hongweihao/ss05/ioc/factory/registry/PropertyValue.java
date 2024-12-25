package io.github.hongweihao.ss05.ioc.factory.registry;

/**
 * <p>
 * PropertyValue
 * </p>
 *
 * @date 2022/11/1 13:53
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
