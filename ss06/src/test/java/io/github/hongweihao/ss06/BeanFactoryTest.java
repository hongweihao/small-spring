package io.github.hongweihao.ss06;

import io.github.hongweihao.ss06.bean.TestService;
import io.github.hongweihao.ss06.ioc.context.ApplicationContext;
import io.github.hongweihao.ss06.ioc.context.ApplicationContextClassPathXml;
import io.github.hongweihao.ss06.ioc.factory.BeanFactoryDefault;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReaderXml;
import org.junit.Test;

/**
 * <p>
 * BeanFactoryTest
 * </p>
 */

public class BeanFactoryTest {

    /**
     * <p>
     * 不使用扩展对象
     * </p>
     */
    @Test
    public void test() {
        BeanFactoryDefault defaultListableBeanFactory = new BeanFactoryDefault();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXml(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }

    /**
     * 使用应用上下文测试
     */
    @Test
    public void test_application_context() {
        ApplicationContext context = new ApplicationContextClassPathXml("classpath:spring.xml");
        TestService testService = context.getBean("testService", TestService.class);
        testService.test();
    }

}
