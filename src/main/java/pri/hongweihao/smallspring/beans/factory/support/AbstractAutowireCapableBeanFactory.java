package pri.hongweihao.smallspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.PropertyValue;
import pri.hongweihao.smallspring.beans.PropertyValues;
import pri.hongweihao.smallspring.beans.factory.*;
import pri.hongweihao.smallspring.beans.factory.config.AutowireCapableBeanFactory;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;
import pri.hongweihao.smallspring.beans.factory.config.BeanReference;
import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 实现创建并存储bean
 * </p>
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    //private final InstantiationStrategy instantiationStrategy = new JDKInstantiationStrategyImpl();
    private final InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategyImpl();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        // 创建实例
        Object instance = createInstance(beanName, beanDefinition, args);

        // 属性填充
        applyPropertyValue(beanName, instance, beanDefinition);

        // 执行初始化方法以及钩子方法
        Object wrapperBean = this.initializeBean(beanName, instance, beanDefinition);

        registerDisposableBeanIfNecessary(beanName, wrapperBean, beanDefinition);

        // 放入Registry
        super.register(beanName, wrapperBean);

        return wrapperBean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {
        registerDisposableBean(beanName, new DisposableBeanAdapter(beanName, wrapperBean, beanDefinition));
    }

    /**
     * <p>
     * 根据参数列表，获取匹配的构造方法初始化类实例
     * </p>
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @param args           args
     * @return java.lang.Object
     */
    private Object createInstance(String beanName, BeanDefinition beanDefinition, Object... args) {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Optional<Constructor> first = Arrays.stream(constructors)
                // 参数个数匹配
                .filter(constructor -> constructor.getParameterTypes().length == args.length)
                .findFirst();
        try {
            return instantiationStrategy.createBean(beanDefinition, first.orElse(null), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Failed to initialize for " + beanName);
        }
    }


    private void applyPropertyValue(String beanName, Object instance, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果需要填充的属性是 BeanFactory 管理的对象，那么递归获取
                if (value instanceof BeanReference) {
                    value = getBean(((BeanReference) value).getBeanName());
                }

                BeanUtil.setFieldValue(instance, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Failed to set properties for " + beanName);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }

            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getClassLoader());
            }

            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        Object wrapperBean = this.applyBeanPostProcessorsBeforeInitialization(beanName, bean);

        try {
            this.initMethods(beanName, wrapperBean, beanDefinition);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new BeansException("Failed to invoke the init method of bean " + beanName, e);
        }
        return this.applyBeanPostProcessorsAfterInitialization(beanName, wrapperBean);
    }

    private Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object bean) {
        for (BeanPostProcessor beanPostProcessor : getPostBeanProcessorList()) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }
        return bean;
    }

    private void initMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws InvocationTargetException, IllegalAccessException {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();

            // XML 配置的方法名相同，无需重复执行
            if ("afterPropertiesSet".equals(beanDefinition.getInitMethodName())) {
                return;
            }
        }

        if (StrUtil.isNotBlank(beanDefinition.getInitMethodName())) {
            Method method;
            try {
                method = bean.getClass().getMethod(beanDefinition.getInitMethodName());
            } catch (NoSuchMethodException e) {
                throw new BeansException("Could not find method " + beanDefinition.getInitMethodName() + " in bean " + beanName);
            }
            method.invoke(bean);
        }
    }

    private Object applyBeanPostProcessorsAfterInitialization(String beanName, Object bean) {
        for (BeanPostProcessor beanPostProcessor : getPostBeanProcessorList()) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

}
