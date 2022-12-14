package pri.hongweihao.smallspring.beans.factory.support;

import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws IOException;

}
