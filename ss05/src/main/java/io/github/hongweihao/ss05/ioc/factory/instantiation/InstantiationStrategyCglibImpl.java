package io.github.hongweihao.ss05.ioc.factory.instantiation;

import io.github.hongweihao.ss05.ioc.factory.registry.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * <p>
 * cglib 初始化对象
 * </p>
 *
 * @date 2022/10/27 13:44
 */
public class InstantiationStrategyCglibImpl implements InstantiationStrategy {
    @SuppressWarnings("rawtypes")
    @Override
    public Object createBean(BeanDefinition beanDefinition, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (Objects.isNull(constructor)) {
            return enhancer.create();
        }

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
