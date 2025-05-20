package io.github.hongweihao.ss09.ioc.factory.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性列表
 * </p>
 *
 * @date 2022/11/1 13:53
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues;


    public PropertyValues() {
        this.propertyValues = new ArrayList<>();
    }

    public PropertyValues(PropertyValue... propertyValues) {
        this.propertyValues = Arrays.stream(propertyValues).collect(Collectors.toList());
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValues(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }
}
