package io.github.hongweihao.ss06.ioc.context;


import io.github.hongweihao.ss06.ioc.factory.BeanFactoryDefault;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReaderXml;

/**
 * 上下文应用实现类
 * 用户入口类
 */
public class ApplicationContextClassPathXml extends ApplicationContextBase {
    private final String[] configurations;

    public ApplicationContextClassPathXml(String configuration) {
        this(new String[]{configuration});
    }

    public ApplicationContextClassPathXml(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    protected String[] getConfigurations() {
        return this.configurations;
    }

    protected void loadBeanDefinitions(BeanFactoryDefault beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXml(beanFactory);
        String[] configurations = getConfigurations();
        if (configurations != null) {
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }
}
