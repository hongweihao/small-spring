package pri.hongweihao.smallspring.beans.factory.config;


import pri.hongweihao.smallspring.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addPostBeanProcessor(BeanPostProcessor beanPostProcessor);
}
