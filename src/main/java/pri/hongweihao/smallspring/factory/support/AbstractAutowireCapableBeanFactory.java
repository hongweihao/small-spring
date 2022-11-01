package pri.hongweihao.smallspring.factory.support;

import pri.hongweihao.smallspring.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.BeanException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 实现创建并存储bean
 * </p>
 *
 * @author Karl
 * @date 2022/10/26 13:52
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    //private final InstantiationStrategy instantiationStrategy = new JDKInstantiationStrategyImpl();
    private final InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategyImpl();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        instance = createInstance(beanDefinition, args);

        super.register(beanName, instance);
        return instance;
    }

    private Object createInstance(BeanDefinition beanDefinition, Object... args) {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Optional<Constructor> first = Arrays.stream(constructors)
                .filter(constructor -> constructor.getParameterTypes().length == args.length)
                .findFirst();

        try {
            return instantiationStrategy.createBean(beanDefinition, first.orElse(null), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanException("Failed to initialize for this class: " + beanDefinition.getBeanClass().getName());
        }

    }
}
