package io.github.hongweihao.ss07;

import io.github.hongweihao.ss07.bean.TestService;
import io.github.hongweihao.ss07.ioc.context.ApplicationContext;
import io.github.hongweihao.ss07.ioc.context.ApplicationContextClassPathXmlImpl;
import io.github.hongweihao.ss07.ioc.factory.BeanFactoryImpl;
import io.github.hongweihao.ss07.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss07.ioc.resource.reader.BeanDefinitionReaderXmlImpl;
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
        BeanFactoryImpl defaultListableBeanFactory = new BeanFactoryImpl();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }


    /**
     * 使用 PostBeanFactoryProcessor 扩展对象
     *
     */
    @Test
    public void test_with_post_bean_factory_processors() {
        BeanFactoryImpl defaultListableBeanFactory = new BeanFactoryImpl();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 手动执行扩展对象
        //MyBeanFactoryPostProcessor myPostBeanFactoryProcessor = new MyBeanFactoryPostProcessor();
        //myPostBeanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }

    /**
     * 使用 PostBeanFactoryProcessor 扩展对象和 PostBeanFactoryProcessor 扩展对象
     */
    @Test
    public void test_with_all_processors() {
        BeanFactoryImpl defaultListableBeanFactory = new BeanFactoryImpl();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 手动执行扩展对象
        //MyBeanFactoryPostProcessor myPostBeanFactoryProcessor = new MyBeanFactoryPostProcessor();
        //myPostBeanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        // 手动将 PostBeanProcess 注册进容器
        // getBean的时候会执行这些扩展对象
        //BeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        //defaultListableBeanFactory.addPostBeanProcessor(myBeanPostProcessor);

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }


    /**
     * 使用应用上下文测试
     */
    @Test
    public void test_application_context() {
        ApplicationContext context = new ApplicationContextClassPathXmlImpl("classpath:spring.xml");
        TestService testService = context.getBean("testService", TestService.class);
        testService.test();
    }

}
