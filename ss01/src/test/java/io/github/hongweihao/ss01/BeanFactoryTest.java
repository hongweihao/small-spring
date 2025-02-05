package io.github.hongweihao.ss01;

import io.github.hongweihao.ss01.ioc.BeanDefinition;
import io.github.hongweihao.ss01.ioc.BeanFactory;
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

    @Test
    public void test() {
        BeanFactory beanFactory = new BeanFactory();

        TestBean testBean = new TestBean();
        BeanDefinition beanDefinition = new BeanDefinition(testBean);

        beanFactory.registerBeanDefinition("testBean", beanDefinition);

        BeanDefinition xBeanDefinition = beanFactory.getBean("testBean");
        TestBean x1 = (TestBean) xBeanDefinition.getBean();
        x1.test();
    }

}
