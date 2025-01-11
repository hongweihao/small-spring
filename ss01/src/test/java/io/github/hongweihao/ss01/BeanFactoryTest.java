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

        X x = new X();
        BeanDefinition beanDefinition = new BeanDefinition(x);

        beanFactory.registerBeanDefinition("x", beanDefinition);

        BeanDefinition xBeanDefinition = beanFactory.getBean("x");
        X x1 = (X) xBeanDefinition.getBean();
        x1.test();
    }

}
