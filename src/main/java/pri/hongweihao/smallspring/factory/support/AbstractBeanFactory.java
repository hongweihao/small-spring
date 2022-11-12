package pri.hongweihao.smallspring.factory.support;

import pri.hongweihao.smallspring.factory.BeanFactory;
import pri.hongweihao.smallspring.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.BeanException;

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
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name, Object... args) {
        Object singletonBean = super.getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeanException;
}
