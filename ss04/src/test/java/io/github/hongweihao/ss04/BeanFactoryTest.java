package io.github.hongweihao.ss04;

import io.github.hongweihao.ss04.bean.Test2Service;
import io.github.hongweihao.ss04.bean.TestService;
import io.github.hongweihao.ss04.ioc.factory.BeanFactoryDefault;
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
        BeanFactoryDefault defaultListableBeanFactory = new BeanFactoryDefault();

        // 注册bean
        PropertyValue propertyValue = new PropertyValue("name", "KARL");
        PropertyValues propertyValues = new PropertyValues(propertyValue);
        BeanDefinition beanDefinition = new BeanDefinition(TestService.class, propertyValues);
        defaultListableBeanFactory.register("testService", beanDefinition);

        PropertyValue serviceProperty = new PropertyValue("testService", new BeanReference("testService"));
        BeanDefinition test2Definition = new BeanDefinition(Test2Service.class, new PropertyValues(serviceProperty));
        defaultListableBeanFactory.register("test2Service", test2Definition);

        // 从工厂中获取Bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "karl");
        service.test();

        service = (TestService) defaultListableBeanFactory.getBean("testService", 18);
        service.test(); // 单例不会再次执行实例化方法

        Test2Service service2 = (Test2Service) defaultListableBeanFactory.getBean("test2Service");
        service2.test();
    }

}
