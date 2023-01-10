package pri.hongweihao.smallspring.beans.factory;

import pri.hongweihao.smallspring.beans.BeansException;

/**
 * <p>
 * Bean 工厂对象
 * </p>
 */
public interface BeanFactory {
    //Object getBean(String name);
    Object getBean(String name, Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
