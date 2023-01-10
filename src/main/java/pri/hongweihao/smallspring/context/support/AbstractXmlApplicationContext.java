package pri.hongweihao.smallspring.context.support;

import pri.hongweihao.smallspring.beans.factory.support.BeanDefinitionReader;
import pri.hongweihao.smallspring.beans.factory.support.DefaultListableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 模板类。定义加载BeanDefinition的流程：
 * 1.获取配置文件路径
 * 2.调用加载器加载配置文件内容，解析并注册
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configurations = getConfigurations();
        if (configurations != null) {
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }

    protected abstract String[] getConfigurations();
}
