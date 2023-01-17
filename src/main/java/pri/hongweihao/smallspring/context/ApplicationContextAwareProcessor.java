package pri.hongweihao.smallspring.context;

import pri.hongweihao.smallspring.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 * 借助BeanPostProcessor设置上下文对象
 * </p>
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    public ApplicationContextAwareProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(context);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
