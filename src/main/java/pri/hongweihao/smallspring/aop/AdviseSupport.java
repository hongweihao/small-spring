package pri.hongweihao.smallspring.aop;


import org.aopalliance.intercept.MethodInterceptor;

public class AdviseSupport {
    // 目标对象
    private final Object target;

    // 方法拦截器。即用户自定义代理逻辑
    private final MethodInterceptor methodInterceptor;

    // 方法匹配器。用户自定义哪些方法需要被代理
    private final MethodMatcher methodMatcher;


    public AdviseSupport(Object target, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        this.target = target;
        this.methodInterceptor = methodInterceptor;
        this.methodMatcher = methodMatcher;
    }

    public Object getTarget() {
        return target;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
}
