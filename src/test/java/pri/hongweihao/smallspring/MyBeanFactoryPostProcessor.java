package pri.hongweihao.smallspring;

import pri.hongweihao.smallspring.beans.PropertyValue;
import pri.hongweihao.smallspring.beans.factory.ConfigurableListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.factory.config.BeanFactoryPostProcessor;


public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition testServiceBeanDefinition = beanFactory.getBeanDefinition("testService");
        testServiceBeanDefinition.getPropertyValues()
                .addPropertyValues(new PropertyValue("name", "PostBeanFactoryProcessor 修改名字为 Jackson"));
    }
}
