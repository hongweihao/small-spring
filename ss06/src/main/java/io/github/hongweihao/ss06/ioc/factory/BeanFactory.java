package io.github.hongweihao.ss06.ioc.factory;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Bean 工厂对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:29
 */
public interface BeanFactory {
    Object getBean(String name, Object... args);

    <T> Map<String, T> getBeansOfType(Class<T> type);
}
