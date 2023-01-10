package pri.hongweihao.smallspring;

import pri.hongweihao.smallspring.bean.TestService;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(beanName + "前置钩子 执行");
        if (beanName.equals("testService")) {
            TestService testService = (TestService) bean;
            testService.setName("postProcessBeforeInitialization 修改名字为 Mike");
            testService.setAge(10);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println(beanName + "后置钩子 执行");
        if (beanName.equals("testService")) {
            TestService testService = (TestService) bean;
            testService.setName("postProcessAfterInitialization 修改名字为 Jack");
            testService.setAge(20);
        }
        return bean;
    }
}
