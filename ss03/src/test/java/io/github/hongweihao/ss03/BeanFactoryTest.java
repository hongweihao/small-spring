package io.github.hongweihao.ss03;

import io.github.hongweihao.ss03.bean.X;
import io.github.hongweihao.ss03.bean.Y;
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
        BeanDefinition xBeanDefinition = new BeanDefinition(X.class);
        BeanDefinition yBeanDefinition = new BeanDefinition(Y.class);

        // 注册Bean
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("x", xBeanDefinition);
        beanFactory.registerBeanDefinition("y", yBeanDefinition);

        // 从工厂中获取Bean对象
        X x = (X) beanFactory.getBean("x");
        x.x();

        Y y = (Y) beanFactory.getBean("y", "karl");
        y.y();
        y = (Y) beanFactory.getBean("y", 18);
        y.y(); // 单例不会再次执行实例化方法
    }

    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(X.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create();
        X x = (X) o;
        x.x();
    }

}
