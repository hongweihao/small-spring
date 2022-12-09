package pri.hongweihao.smallspring.factory.support;

import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws IOException;

}
