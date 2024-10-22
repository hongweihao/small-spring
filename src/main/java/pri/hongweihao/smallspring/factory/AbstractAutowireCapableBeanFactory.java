package pri.hongweihao.smallspring.factory;

import pri.hongweihao.smallspring.BeanDefinition;
import pri.hongweihao.smallspring.BeanException;
import pri.hongweihao.smallspring.factory.strategy.CglibInstantiationStrategyImpl;
import pri.hongweihao.smallspring.factory.strategy.JDKInstantiationStrategyImpl;

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
    // private final InstantiationStrategy strategy = new
    // JDKInstantiationStrategyImpl();
    private final InstantiationStrategy strategy = new CglibInstantiationStrategyImpl();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object instance;
        try {
            instance = createInstance(beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize:" + beanDefinition.getBeanClass().getName(), e);
        }
        addSingleton(beanName, instance);
        return instance;
    }

    @SuppressWarnings("rawtypes")
    private Object createInstance(BeanDefinition beanDefinition, Object... args) {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Optional<Constructor> matchingConstructor = Arrays.stream(constructors)
                .filter(constructor -> {
                    // 通过构造方法参数列表长度和类型选择对应的构造方法
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length != args.length) {
                        return false;
                    }
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (args[i] != null && !parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                            return false;
                        }
                    }
                    return true;
                }).findFirst();

        try {
            return strategy.createBean(beanDefinition, matchingConstructor.orElse(null), args);
        } catch (Exception e) {
            throw new BeanException("Failed to initialize " + beanDefinition.getBeanClass().getName(), e);
        }
    }
}
