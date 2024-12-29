package io.github.hongweihao.ss06.ioc.context;


import io.github.hongweihao.ss06.ioc.factory.BeanFactoryImpl;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss06.ioc.resource.reader.BeanDefinitionReaderXmlImpl;

/**
 * 上下文应用实现类
 * 用户入口类
 */
public class ApplicationContextClassPathXmlImpl extends ApplicationContextBase {
    private final String[] configurations;

    public ApplicationContextClassPathXmlImpl(String configuration) {
        this(new String[]{configuration});
    }

    public ApplicationContextClassPathXmlImpl(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    protected String[] getConfigurations() {
        return this.configurations;
    }

    protected void loadBeanDefinitions(BeanFactoryImpl beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReaderXmlImpl(beanFactory);
        String[] configurations = getConfigurations();
        if (configurations != null) {
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }
}
