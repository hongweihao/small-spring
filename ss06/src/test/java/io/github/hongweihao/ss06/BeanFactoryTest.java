package io.github.hongweihao.ss06;

import io.github.hongweihao.ss06.bean.TestService;
import io.github.hongweihao.ss06.ioc.factory.BeanFactoryPostProcessor;
import io.github.hongweihao.ss06.ioc.factory.DefaultListableBeanFactory;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss06.ioc.resource.reader.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * <p>
 * BeanFactoryTest
 * </p>
 */

public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 获取BeanFactoryPostProcessor的实现类并执行其方法
        Map<String, BeanFactoryPostProcessor> beanMap = defaultListableBeanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        beanMap.forEach((beanName, beanFactoryPostProcessor) -> {
            beanFactoryPostProcessor.postProcessBeanFactory(defaultListableBeanFactory);
        });


        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", TestService.class);
        service.test();
    }


}
