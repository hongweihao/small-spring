package pri.hongweihao.smallspring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import pri.hongweihao.smallspring.bean.Test2Service;
import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.factory.config.BeanReference;
import pri.hongweihao.smallspring.factory.config.PropertyValue;
import pri.hongweihao.smallspring.factory.config.PropertyValues;
import pri.hongweihao.smallspring.factory.support.DefaultListableBeanFactory;
import pri.hongweihao.smallspring.factory.config.BeanDefinition;

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
        PropertyValue propertyValue = new PropertyValue("name", "KARL");
        PropertyValues propertyValues = new PropertyValues(propertyValue);
        BeanDefinition beanDefinition = new BeanDefinition(TestService.class, propertyValues);
        defaultListableBeanFactory.register("testService", beanDefinition);

        PropertyValue serviceProperty = new PropertyValue("testService", new BeanReference("testService"));
        BeanDefinition test2Definition = new BeanDefinition(Test2Service.class, new PropertyValues(serviceProperty));
        defaultListableBeanFactory.register("test2Service", test2Definition);

        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "karl");
        service.test();

        Test2Service service2 = (Test2Service) defaultListableBeanFactory.getBean("test2Service");
        service2.test();
    }

}
