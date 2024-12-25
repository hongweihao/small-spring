package io.github.hongweihao.ss04.ioc.factory.registry;

/**
 * <p>
 * BeanFactory 管理的对象
 * </p>
 *
 * @author Karl
 * @date 2022/11/1 13:54
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
