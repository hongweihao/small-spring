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

        TestService testService = new TestService();
        BeanDefinition beanDefinition = new BeanDefinition(testService);

        beanFactory.register("testService", beanDefinition);

        BeanDefinition testService1 = beanFactory.getBean("testService");
        TestService service = (TestService) testService1.getBean();
        service.test();
    }

}
