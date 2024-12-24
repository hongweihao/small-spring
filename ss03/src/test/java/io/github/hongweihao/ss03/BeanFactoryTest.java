package io.github.hongweihao.ss03;

import io.github.hongweihao.ss03.bean.Test2Service;
import io.github.hongweihao.ss03.bean.TestService;
import io.github.hongweihao.ss03.ioc.factory.BeanFactoryImpl;
import io.github.hongweihao.ss03.ioc.factory.registry.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
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
        // 准备BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(TestService.class);
        BeanDefinition test2Definition = new BeanDefinition(Test2Service.class);

        BeanFactoryImpl beanFactory = new BeanFactoryImpl();
        // 注册Bean
        beanFactory.register("testService", beanDefinition);
        beanFactory.register("test2Service", test2Definition);

        // 从工厂中获取Bean对象
        TestService service = (TestService) beanFactory.getBean("testService", "karl");
        service.test();

        service = (TestService) beanFactory.getBean("testService", 18);
        service.test(); // 单例不会再次执行实例化方法

        Test2Service service2 = (Test2Service) beanFactory.getBean("test2Service");
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
