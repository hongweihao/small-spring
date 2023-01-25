package pri.hongweihao.smallspring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.PropertyValue;
import pri.hongweihao.smallspring.beans.PropertyValues;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.factory.config.BeanReference;
import pri.hongweihao.smallspring.beans.factory.support.AbstractBeanDefinitionReader;
import pri.hongweihao.smallspring.beans.factory.support.BeanDefinitionRegistry;
import pri.hongweihao.smallspring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        loadBeanDefinitions(resourceLoader.getResource(location));
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputSteam = resource.getInputSteam();
            doLoadBeanDefinitions(inputSteam);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException(e.getMessage());
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (!(item instanceof Element)) continue;
            if (!"bean".equals(item.getNodeName())) continue;

            // bean 信息
            Element element = (Element) item;
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String className = element.getAttribute("class");
            String initMethodName = element.getAttribute("init-method");
            String destroyMethodName = element.getAttribute("destroy-method");
            String scope = element.getAttribute("scope");

            String beanName = StrUtil.isNotBlank(id) ? id : name;
            Class<?> clazz = Class.forName(className);

            PropertyValues propertyValues = new PropertyValues();
            BeanDefinition beanDefinition = new BeanDefinition(clazz, propertyValues, scope);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

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
            beanDefinitionRegistry.register(beanName, beanDefinition);
        }
    }
}
