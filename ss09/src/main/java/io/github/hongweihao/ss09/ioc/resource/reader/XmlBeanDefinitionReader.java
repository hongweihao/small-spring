package io.github.hongweihao.ss09.ioc.resource.reader;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import io.github.hongweihao.ss09.ioc.factory.BeanException;
import io.github.hongweihao.ss09.ioc.factory.registry.*;
import io.github.hongweihao.ss09.ioc.resource.Resource;
import io.github.hongweihao.ss09.ioc.resource.loader.DefaultResourceLoader;
import io.github.hongweihao.ss09.ioc.resource.loader.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void loadBeanDefinitions(String location) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        loadBeanDefinitions(resourceLoader.getResource(location));
    }

    private void loadBeanDefinitions(Resource resource) throws IOException {
        InputStream inputSteam = resource.getInputSteam();
        doLoadBeanDefinitions(inputSteam);
    }

    // private void loadBeanDefinitions(Resource... resources) throws IOException {
    //     for (Resource resource : resources) {
    //         loadBeanDefinitions(resource);
    //     }
    // }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (!(item instanceof Element element)) continue;
            if (!"bean".equals(item.getNodeName())) continue;

            // bean 信息
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String className = element.getAttribute("class");

            String beanName = StrUtil.isNotBlank(id) ? id : name;
            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeanException(e.getMessage());
            }

            PropertyValues propertyValues = new PropertyValues();
            BeanDefinition beanDefinition = new BeanDefinition(clazz, propertyValues);

            // properties 信息
            NodeList propertyNodes = element.getChildNodes();
            for (int j = 0; j < propertyNodes.getLength(); j++) {
                Node property = propertyNodes.item(j);
                if (!(property instanceof Element)) continue;
                if (!"property".equals(property.getNodeName())) continue;

                Element propertyElement = (Element) property;

                String propertyName = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                String ref = propertyElement.getAttribute("ref");

                PropertyValue propertyValue;
                if (StrUtil.isNotBlank(ref)) {
                    BeanReference beanReference = new BeanReference(ref);
                    propertyValue = new PropertyValue(propertyName, beanReference);
                } else {
                    propertyValue = new PropertyValue(propertyName, value);
                }
                propertyValues.addPropertyValues(propertyValue);
            }

            // 注册
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
