package pri.hongweihao.smallspring.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import pri.hongweihao.smallspring.aop.*;
import pri.hongweihao.smallspring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import pri.hongweihao.smallspring.aop.framework.ProxyFactory;
import pri.hongweihao.smallspring.beans.factory.BeanFactory;
import pri.hongweihao.smallspring.beans.factory.BeanFactoryAware;
import pri.hongweihao.smallspring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import pri.hongweihao.smallspring.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>
 * 负责从BeanFactory中获取Advisor列表，获取 Advisor 中用户自定义的切面以及切点信息
 * 构建 AdviceSupport 并创建代理对象
 * </p>
 *
 * @author Karl
 * @date 2023/3/21 13:53
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }


    /**
     * <p>
     * 在对象初始化前调用
     * </p>
     *
     * @param beanClass beanClass
     * @param beanName  beanName
     * @return java.lang.Object
     */
    @Override
    public Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName) {
        if (isInfrastructureClass(beanClass)) {
            // 基础功能类，不需要代理
            return null;
        }

        Map<String, AspectJExpressionPointcutAdvisor> advisorMap = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisorMap.values()) {
            // 判断类切点，有切点才需要代理
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                return null;
            }

            Object target = null;
            try {
                target = beanClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
            MethodInterceptor methodInterceptor = (MethodInterceptor) advisor.getAdvice();
            MethodMatcher methodMatcher = advisor.getPointcut().getMethodMatcher();
            boolean proxyTargetClass = false;
            AdviseSupport adviseSupport = new AdviseSupport();
            adviseSupport.setTarget(target);
            adviseSupport.setMethodInterceptor(methodInterceptor);
            adviseSupport.setMethodMatcher(methodMatcher);
            adviseSupport.setProxyTargetClass(proxyTargetClass);

            // 这里执行了一次代理而已
            return new ProxyFactory(adviseSupport).getProxy();
        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advisor.class.isAssignableFrom(beanClass) || Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
