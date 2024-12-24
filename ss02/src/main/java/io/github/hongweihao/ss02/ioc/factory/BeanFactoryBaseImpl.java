package io.github.hongweihao.ss02.ioc.factory;



import io.github.hongweihao.ss02.ioc.factory.registry.BeanDefinition;
import io.github.hongweihao.ss02.ioc.factory.registry.SingletonBeanRegistryImpl;

import java.util.Objects;

/**
 * <p>
 * BeanFactory 模板类
 * 定义了获取 bean 的流程
 * 1.根据 beanName 获取。如果获取到则返回，否则下一步
 * 2.获取 beanName 对应的 BeanDefinition 信息
 * 3.创建 beanName 对应的单例对象，并调用 registry 的注册方法存储
 * 4.返回单例对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:45
 */
public abstract class BeanFactoryBaseImpl extends SingletonBeanRegistryImpl implements BeanFactory {
    @Override
    public Object getBean(String name) {
        Object singletonBean = super.getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object instance;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            throw new BeanException("Cannot instantiation a bean: " + beanDefinition.getBeanClass().getName());
        }
        super.register(beanName, instance);
        return instance;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
