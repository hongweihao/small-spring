package io.github.hongweihao.ss07.ioc.resource.reader;

import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws IOException;
}
