package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.core.io.Resource;
import pri.hongweihao.smallspring.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;

}
