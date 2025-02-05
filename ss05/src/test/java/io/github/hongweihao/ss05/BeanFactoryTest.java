package io.github.hongweihao.ss05;

import io.github.hongweihao.ss05.bean.TestService;
import io.github.hongweihao.ss05.ioc.factory.BeanFactoryDefault;
import io.github.hongweihao.ss05.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss05.ioc.resource.reader.BeanDefinitionReaderXml;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>
 * BeanFactoryTest
 * </p>
 *
 * @date 2022/10/25 13:34
 */

public class BeanFactoryTest {

    /**
     * <p>
     * 测试读取spring.xml并自动注册进registry
     * </p>
     *
     *
     * @date 2022/11/12 14:53
     */
    @Test
    public void test() throws IOException {
        BeanFactoryDefault defaultListableBeanFactory = new BeanFactoryDefault();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXml(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");


        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "", null);
        service.test();
    }
}
