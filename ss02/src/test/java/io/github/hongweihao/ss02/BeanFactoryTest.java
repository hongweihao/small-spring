package io.github.hongweihao.ss02;

import io.github.hongweihao.ss02.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss02.ioc.factory.BeanFactoryDefault;
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
        // 大杂烩把工厂准备好
        BeanFactoryDefault defaultListableBeanFactory = new BeanFactoryDefault();

        // 登记
        BeanDefinition beanDefinition = new BeanDefinition(SuperMungBean.class);
        defaultListableBeanFactory.register("superMungBean", beanDefinition);

        // 取豆子
        SuperMungBean service = (SuperMungBean) defaultListableBeanFactory.getBean("superMungBean");

        // 测试看看豆子好不好吃
        service.test();
    }
}
