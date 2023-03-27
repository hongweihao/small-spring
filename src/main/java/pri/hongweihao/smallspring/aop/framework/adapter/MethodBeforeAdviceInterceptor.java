package pri.hongweihao.smallspring.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import pri.hongweihao.smallspring.aop.MethodBeforeAdvise;

/**
 * <p>
 * 固定反射前执行流程
 * </p>
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvise methodBeforeAdvise;

    public MethodBeforeAdviceInterceptor() {
    }


    public MethodBeforeAdviceInterceptor(MethodBeforeAdvise methodBeforeAdvise) {
        this.methodBeforeAdvise = methodBeforeAdvise;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        methodBeforeAdvise.before(methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
        return methodInvocation.proceed();
    }
}
