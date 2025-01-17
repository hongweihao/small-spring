package io.github.hongweihao.ss04;

import io.github.hongweihao.ss04.bean.Y;
import io.github.hongweihao.ss04.bean.X;
import io.github.hongweihao.ss04.bean.XX;
import io.github.hongweihao.ss04.bean.YY;
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
        PropertyValues xPropertyValues = new PropertyValues();
        xPropertyValues.add(new PropertyValue("name", "x name"));
        defaultListableBeanFactory.registerBeanDefinition("x", new BeanDefinition(X.class, xPropertyValues));

        PropertyValues yPropertyValues = new PropertyValues();
        yPropertyValues.add(new PropertyValue("x", new BeanReference("x")));
        yPropertyValues.add(new PropertyValue("name", "y name"));
        defaultListableBeanFactory.registerBeanDefinition("y",  new BeanDefinition(Y.class, yPropertyValues));

        PropertyValues xxPropertyValues = new PropertyValues();
        xxPropertyValues.add(new PropertyValue("name", "xx name"));
        xxPropertyValues.add(new PropertyValue("description", "xx description"));
        defaultListableBeanFactory.registerBeanDefinition("xx", new BeanDefinition(XX.class, xxPropertyValues));

        PropertyValues yyPropertyValues = new PropertyValues();
        yyPropertyValues.add(new PropertyValue("x", new BeanReference("x")));
        yyPropertyValues.add(new PropertyValue("name", "yy name"));
        yyPropertyValues.add(new PropertyValue("description", "yy description"));
        defaultListableBeanFactory.registerBeanDefinition("yy",  new BeanDefinition(YY.class, yyPropertyValues));


        // 从工厂中获取Bean对象
        X x = (X) defaultListableBeanFactory.getBean("x");
        x.x();

        Y y = (Y) defaultListableBeanFactory.getBean("y");
        y.y();

        XX xx = (XX) defaultListableBeanFactory.getBean("xx");
        xx.xx();

        YY yy = (YY) defaultListableBeanFactory.getBean("yy");
        yy.yy();
    }

}
