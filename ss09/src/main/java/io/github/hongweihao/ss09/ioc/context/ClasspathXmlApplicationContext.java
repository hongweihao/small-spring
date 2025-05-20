package io.github.hongweihao.ss09.ioc.context;

import io.github.hongweihao.ss09.ioc.factory.BeanException;
import io.github.hongweihao.ss09.ioc.resource.reader.BeanDefinitionReader;
import io.github.hongweihao.ss09.ioc.resource.reader.XmlBeanDefinitionReader;

public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

    private String[] configurations;

    public ClasspathXmlApplicationContext(String... configurations) {
        this.configurations = configurations;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions() {
        try {
            BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(getBeanFactory());
            for (String configurations : configurations) {
                beanDefinitionReader.loadBeanDefinitions(configurations);
            }
        } catch (Exception e) {
            throw new BeanException("Failed to load bean definitions", e);
        }
    }
}
