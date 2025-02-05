package io.github.hongweihao.ss03;

import io.github.hongweihao.ss03.bean.TestBean;
import io.github.hongweihao.ss03.bean.TestBean2;
import io.github.hongweihao.ss03.ioc.factory.DefaultListableBeanFactory;
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
        BeanDefinition beanDefinition = new BeanDefinition(TestBean.class);
        BeanDefinition beanDefinition2 = new BeanDefinition(TestBean2.class);

        // 注册Bean
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("testBean", beanDefinition);
        beanFactory.registerBeanDefinition("testBean2", beanDefinition2);

        // 从工厂中获取Bean对象
        TestBean testBean = (TestBean) beanFactory.getBean("testBean");
        testBean.test();

        TestBean2 testBean2 = (TestBean2) beanFactory.getBean("testBean2", "karl");
        testBean2.test();
        testBean2 = (TestBean2) beanFactory.getBean("testBean2", 18);
        testBean2.test(); // 单例不会再次执行实例化方法
    }

    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestBean.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create();
        TestBean testBean = (TestBean) o;
        testBean.test();
    }

}
