package io.github.hongweihao.ss04;

import io.github.hongweihao.ss04.bean.TestBean2;
import io.github.hongweihao.ss04.bean.TestBean;
import io.github.hongweihao.ss04.bean.TestSubBean;
import io.github.hongweihao.ss04.bean.TestSubBean2;
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
        PropertyValues testBeanPropertyValues = new PropertyValues();
        testBeanPropertyValues.add(new PropertyValue("name", "TestBean name"));
        defaultListableBeanFactory.registerBeanDefinition("testBean", new BeanDefinition(TestBean.class, testBeanPropertyValues));

        PropertyValues testBean2PropertyValues = new PropertyValues();
        testBean2PropertyValues.add(new PropertyValue("testBean", new BeanReference("testBean")));
        testBean2PropertyValues.add(new PropertyValue("name", "TestBean2 name"));
        defaultListableBeanFactory.registerBeanDefinition("testBean2",  new BeanDefinition(TestBean2.class, testBean2PropertyValues));

        PropertyValues testSubBeanPropertyValues = new PropertyValues();
        testSubBeanPropertyValues.add(new PropertyValue("name", "TestSubBean name"));
        testSubBeanPropertyValues.add(new PropertyValue("description", "TestSubBean description"));
        defaultListableBeanFactory.registerBeanDefinition("testSubBean", new BeanDefinition(TestSubBean.class, testSubBeanPropertyValues));

        PropertyValues testSubBean2PropertyValues = new PropertyValues();
        testSubBean2PropertyValues.add(new PropertyValue("testBean", new BeanReference("testBean")));
        testSubBean2PropertyValues.add(new PropertyValue("name", "TestSubBean2 name"));
        testSubBean2PropertyValues.add(new PropertyValue("description", "TestSubBean2 description"));
        defaultListableBeanFactory.registerBeanDefinition("testSubBean2",  new BeanDefinition(TestSubBean2.class, testSubBean2PropertyValues));


        // 从工厂中获取Bean对象
        TestBean testBean = (TestBean) defaultListableBeanFactory.getBean("testBean");
        testBean.test();

        TestBean2 testBean2 = (TestBean2) defaultListableBeanFactory.getBean("testBean2");
        testBean2.test();

        TestSubBean testSubBean = (TestSubBean) defaultListableBeanFactory.getBean("testSubBean");
        testSubBean.test();

        TestSubBean2 testSubBean2 = (TestSubBean2) defaultListableBeanFactory.getBean("testSubBean2");
        testSubBean2.test();
    }

}
