package pri.hongweihao.smallspring;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import pri.hongweihao.smallspring.bean.Test2Service;
import pri.hongweihao.smallspring.bean.TestService;
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

        BeanDefinition test2Definition = new BeanDefinition(Test2Service.class);
        defaultListableBeanFactory.register("test2Service", test2Definition);

        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "karl");
        service.test();

        Test2Service service2 = (Test2Service) defaultListableBeanFactory.getBean("test2Service");
        service2.test();
    }

    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Test2Service.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o= enhancer.create();
        Test2Service service = (Test2Service) o;
        service.test();
    }

}
