package pri.hongweihao.smallspring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import pri.hongweihao.smallspring.bean.Test2Service;
import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.factory.config.BeanReference;
import pri.hongweihao.smallspring.factory.config.PropertyValue;
import pri.hongweihao.smallspring.factory.config.PropertyValues;
import pri.hongweihao.smallspring.factory.support.DefaultListableBeanFactory;
import pri.hongweihao.smallspring.factory.config.BeanDefinition;

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
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 读取配置文件并自动注册
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");


        // 从工厂中获取bean对象
        TestService service = (TestService) defaultListableBeanFactory.getBean("testService", "", null);
        service.test();
    }

}
