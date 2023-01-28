package pri.hongweihao.smallspring.beans.factory.support;

import pri.hongweihao.smallspring.beans.factory.FactoryBean;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;
import pri.hongweihao.smallspring.beans.factory.config.ConfigurableBeanFactory;
import pri.hongweihao.smallspring.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;
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
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    private final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    private Object doGetBean(String name, Object... args) {
        Object singletonBean = super.getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            // 查询到单例对象，直接返回
            return getObjectForInstance(singletonBean, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return getObjectForInstance(bean, name);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;

    @Override
    public void addPostBeanProcessor(BeanPostProcessor beanPostProcessor) {
        // 相当于调整了顺序，让新的往前放
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getPostBeanProcessorList() {
        return this.beanPostProcessorList;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    private Object getObjectForInstance(Object instance, String beanName) {
        if (!(instance instanceof FactoryBean)) {
            return instance;
        }

        // 这里两次查询，因为getObject 方法内部会根据类型决定创建完是否加入缓存
        Object object = super.getObjectFromCache(beanName);
        if (Objects.isNull(object)) {
            object = super.getObject((FactoryBean<?>) instance, beanName);
        }
        return object;
    }
}
