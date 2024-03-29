package pri.hongweihao.smallspring.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import pri.hongweihao.smallspring.aop.aspectj.AspectJExpressionPointcut;
import pri.hongweihao.smallspring.aop.framework.AopProxy;
import pri.hongweihao.smallspring.aop.framework.CGlibAopProxy;
import pri.hongweihao.smallspring.aop.framework.JDKDynamicAopProxy;
import pri.hongweihao.smallspring.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test() {
        // 被代理的目标对象
        ITarget target = new Target();

        // 自定义需要被代理的方法
        AspectJExpressionPointcut aspectJExpressionPointCut = new AspectJExpressionPointcut("execution(* pri.hongweihao.smallspring.aop.ITarget.*(..))");

        // 自定义代理逻辑
        MethodInterceptor methodInterceptor = methodInvocation -> {
            System.out.println("Before");
            Object proceed = methodInvocation.proceed();
            System.out.println("After");
            return proceed;
        };

        // 自定义内容
        AdviseSupport adviseSupport = new AdviseSupport();
        adviseSupport.setTarget(target);
        adviseSupport.setMethodInterceptor(methodInterceptor);
        adviseSupport.setMethodMatcher(aspectJExpressionPointCut);
        adviseSupport.setProxyTargetClass(false);


        // JDK 动态代理
        AopProxy jdkDynamicAopProxy = new JDKDynamicAopProxy(adviseSupport);
        Object proxy = jdkDynamicAopProxy.getProxy();
        ITarget jdkProxy = (ITarget) proxy;
        jdkProxy.method();

        // cglib 代理
        AopProxy cglibAopProxy = new CGlibAopProxy(adviseSupport);
        ITarget cglibProxy = (ITarget) cglibAopProxy.getProxy();
        cglibProxy.method();
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ITarget target = applicationContext.getBean("target", ITarget.class);
        target.method();
    }
}
