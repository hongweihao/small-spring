package io.github.hongweihao.ss02.ioc.factory;

/**
 * <p>
 * Bean 工厂对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:29
 */
public interface BeanFactory {
    Object getBean(String name);
}
