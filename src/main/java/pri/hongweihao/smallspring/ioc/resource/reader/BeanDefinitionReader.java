package pri.hongweihao.smallspring.ioc.resource.reader;

import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws IOException;
}
