package pri.hongweihao.smallspring;

import org.junit.Test;
import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;
import pri.hongweihao.smallspring.beans.factory.support.BeanDefinitionReader;
import pri.hongweihao.smallspring.beans.factory.support.DefaultListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.xml.XmlBeanDefinitionReader;
import pri.hongweihao.smallspring.context.ConfigurableApplicationContext;
import pri.hongweihao.smallspring.context.support.ClassPathXmlApplicationContext;

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
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
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
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 手动执行扩展对象
        MyBeanFactoryPostProcessor myPostBeanFactoryProcessor = new MyBeanFactoryPostProcessor();
        myPostBeanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }

    /**
     * 使用 PostBeanFactoryProcessor 扩展对象和 PostBeanFactoryProcessor 扩展对象
     */
    @Test
    public void test_with_all_processors() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 手动执行扩展对象
        MyBeanFactoryPostProcessor myPostBeanFactoryProcessor = new MyBeanFactoryPostProcessor();
        myPostBeanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        // 手动将 PostBeanProcess 注册进容器
        // getBean的时候会执行这些扩展对象
        BeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        defaultListableBeanFactory.addPostBeanProcessor(myBeanPostProcessor);

        // 从工厂中获取bean对象
        TestService service = defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }


    /**
     * 使用应用上下文测试
     */
    @Test
    public void test_application_context() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        TestService testService = context.getBean("testService", TestService.class);
        testService.test();
    }

}
