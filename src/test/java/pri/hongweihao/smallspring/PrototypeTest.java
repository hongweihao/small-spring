package pri.hongweihao.smallspring;

import org.junit.Assert;
import org.junit.Test;
import pri.hongweihao.smallspring.bean.TestDao;
import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.context.ConfigurableApplicationContext;
import pri.hongweihao.smallspring.context.support.ClassPathXmlApplicationContext;

public class PrototypeTest {
    @Test
    public void test() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        TestDao testDao1 = context.getBean("testDao", TestDao.class);
        TestDao testDao2 = context.getBean("testDao", TestDao.class);
        Assert.assertEquals(testDao1, testDao2);

        TestService testService1 = context.getBean("testService", TestService.class);
        TestService testService2 = context.getBean("testService", TestService.class);
        Assert.assertNotEquals(testService1, testService2);
    }
}
