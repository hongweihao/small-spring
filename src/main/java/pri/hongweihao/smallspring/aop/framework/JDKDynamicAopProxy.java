package pri.hongweihao.smallspring.aop.framework;


import pri.hongweihao.smallspring.aop.AdviseSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdviseSupport adviseSupport;

    public JDKDynamicAopProxy(AdviseSupport adviseSupport) {
        this.adviseSupport = adviseSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), adviseSupport.getTarget().getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 匹配到方法才执行用户自定义的代理逻辑
        if (adviseSupport.getMethodMatcher().matches(method, adviseSupport.getTarget().getClass())) {
            return adviseSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(adviseSupport.getTarget(), method, args));
        }
        return method.invoke(adviseSupport.getTarget(), args);
    }
}
