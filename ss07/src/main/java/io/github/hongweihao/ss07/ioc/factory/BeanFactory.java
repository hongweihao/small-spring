package io.github.hongweihao.ss07.ioc.factory;

import java.util.Map;

/**
 * <p>
 * Bean 工厂对象
 * </p>
 *
 * @date 2022/10/25 13:29
 */
public interface BeanFactory {
    //Object getBean(String name);
    Object getBean(String name, Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeanException;

    <T> Map<String, T> getBeansOfType(Class<T> type);

    String[] getBeanDefinitionNames();
}
