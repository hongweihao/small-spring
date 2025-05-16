package io.github.hongweihao.ss08.ioc.resource.reader;

import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws IOException;
}
