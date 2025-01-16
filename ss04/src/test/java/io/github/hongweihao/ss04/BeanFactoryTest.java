package io.github.hongweihao.ss04;

import io.github.hongweihao.ss04.bean.Y;
import io.github.hongweihao.ss04.bean.X;
import io.github.hongweihao.ss04.ioc.factory.DefaultListableBeanFactory;
import io.github.hongweihao.ss04.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss04.ioc.factory.registry.BeanReference;
import io.github.hongweihao.ss04.ioc.factory.registry.PropertyValue;
import io.github.hongweihao.ss04.ioc.factory.registry.PropertyValues;
import org.junit.Test;


/**
 * <p>
 * BeanFactoryTest
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:34
 */

public class BeanFactoryTest {

    /**
     * <p>
     * 测试属性自动填充
     * </p>
     *
     * @author Karl
     * @date 2022/11/12 14:53
     */
    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 注册bean
        PropertyValue propertyValue = new PropertyValue("name", "x");
        PropertyValues propertyValues = new PropertyValues(propertyValue);
        BeanDefinition beanDefinition = new BeanDefinition(X.class, propertyValues);
        defaultListableBeanFactory.registerBeanDefinition("x", beanDefinition);


        PropertyValue xProperty = new PropertyValue("x", new BeanReference("x"));
        PropertyValue nameProperty = new PropertyValue("name", "y");
        BeanDefinition test2Definition = new BeanDefinition(Y.class, new PropertyValues(xProperty, nameProperty));
        defaultListableBeanFactory.registerBeanDefinition("y", test2Definition);

        // 从工厂中获取Bean对象
        X x = (X) defaultListableBeanFactory.getBean("x");
        x.x();

        Y y = (Y) defaultListableBeanFactory.getBean("y");
        y.y();
    }

}
