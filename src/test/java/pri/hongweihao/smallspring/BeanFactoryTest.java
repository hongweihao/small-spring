package pri.hongweihao.smallspring;

import org.junit.Test;
import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.ioc.factory.BeanFactoryImpl;
import pri.hongweihao.smallspring.ioc.resource.reader.BeanDefinitionReader;
import pri.hongweihao.smallspring.ioc.resource.reader.BeanDefinitionReaderXmlImpl;



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
        BeanFactoryImpl defaultListableBeanFactory = new BeanFactoryImpl();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");


        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "", null);
        service.test();
        /*
        打印结果
        testService.namekarl
        testDao
         */
    }
}
