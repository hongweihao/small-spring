package pri.hongweihao.smallspring.factory.support;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pri.hongweihao.smallspring.core.io.DefaultResourceLoader;
import pri.hongweihao.smallspring.core.io.Resource;
import pri.hongweihao.smallspring.core.io.ResourceLoader;
import pri.hongweihao.smallspring.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.factory.config.PropertyValue;
import pri.hongweihao.smallspring.factory.config.PropertyValues;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry beanDefinitionRegistry = new DefaultListableBeanFactory();

    @Override
    public void loadBeanDefinitions(String location) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        loadBeanDefinitions(resourceLoader.getResource(location));
    }

    private void loadBeanDefinitions(Resource resource) throws IOException {
        InputStream inputSteam = resource.getInputSteam();
        doLoadBeanDefinitions(inputSteam);
    }

    private void loadBeanDefinitions(Resource... resources) throws IOException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();








        /*Class<?> clazz = null;
        try {
            clazz = Class.forName("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PropertyValue propertyValue = new PropertyValue("name", "value");
        PropertyValues propertyValues = new PropertyValues(propertyValue);
        BeanDefinition beanDefinition = new BeanDefinition(clazz, propertyValues);

        beanDefinitionRegistry.register("", beanDefinition);*/
    }
}
