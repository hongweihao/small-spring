package pri.hongweihao.smallspring.aop;


import org.aopalliance.intercept.MethodInterceptor;

public class AdviseSupport {
    // 目标对象
    private Object target;

    // 方法拦截器。即用户自定义代理逻辑
    private MethodInterceptor methodInterceptor;

    // 方法匹配器。用户自定义哪些方法需要被代理
    private MethodMatcher methodMatcher;

    private boolean proxyTargetClass;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
}
