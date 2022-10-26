package pri.hongweihao.smallspring;

import org.junit.Test;
import pri.hongweihao.smallspring.factory.DefaultListableBeanFactory;

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
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(TestService.class);
        defaultListableBeanFactory.register("testService", beanDefinition);

        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService");
        service.test();
    }

}
