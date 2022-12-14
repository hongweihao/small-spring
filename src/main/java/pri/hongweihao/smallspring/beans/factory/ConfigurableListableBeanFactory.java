package pri.hongweihao.smallspring.beans.factory;


import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);
}
