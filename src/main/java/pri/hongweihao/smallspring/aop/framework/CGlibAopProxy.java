package pri.hongweihao.smallspring.aop.framework;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import pri.hongweihao.smallspring.aop.AdviseSupport;

import java.lang.reflect.Method;

public class CGlibAopProxy implements AopProxy {

    private final AdviseSupport adviseSupport;

    public CGlibAopProxy(AdviseSupport adviseSupport) {
        this.adviseSupport = adviseSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(adviseSupport.getTarget().getClass());
        enhancer.setInterfaces(adviseSupport.getTarget().getClass().getInterfaces());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (adviseSupport.getMethodMatcher().matches(method, adviseSupport.getTarget().getClass())) {
                    return adviseSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(adviseSupport.getTarget(), method, args));
                }

                //return method.invoke(adviseSupport.getTarget(), args);
                return new CGlibMethodInvocation(adviseSupport, method, args, methodProxy).proceed();

            }
        });
        return enhancer.create();
    }

    private static class CGlibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;

        public CGlibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.getThis(), getArguments());
        }
    }
}
