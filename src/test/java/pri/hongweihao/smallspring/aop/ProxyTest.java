package pri.hongweihao.smallspring.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * JdkProxyTest
 * </p>
 *
 * @author Karl
 * @date 2023/2/23 13:19
 */
public class ProxyTest {

    /**
     * <p>
     * 使用 JDK 创建代理对象
     * 使用 Method 对象对目标对象进行调用
     * </p>
     *
     * @author Karl
     * @date 2023/2/23 13:51
     */
    @Test
    public void JDK_test() {
        // 1.创建目标对象
        Target target = new Target();

        // 2.创建代理对象。重点是 InvocationHandler 对象的初始化
        ITarget targetProxy = (ITarget) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 3.定义代理逻辑
                System.out.println("proxy method start");

                // 注意这里是反射调用target对象方法，不是调用proxy对象方法
                Object invoke = method.invoke(target, args);

                System.out.println("proxy method end");

                return invoke;
            }
        });

        // 4.执行方法
        targetProxy.method();
    }

    /**
     * <p>
     * 使用 Cglib 创建代理对象
     * 使用 Method 对象对目标对象进行调用
     * </p>
     *
     * @author Karl
     * @date 2023/2/23 13:50
     */
    @Test
    public void Cglib_test() {
        // 1.创建目标对象
        Target target = new Target();

        // 2.创建代理对象，重点是 MethodInterceptor 对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setInterfaces(target.getClass().getInterfaces());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                // 3.定义代理逻辑
                System.out.println("proxy method start");

                Object invoke = methodProxy.invoke(target, args);

                System.out.println("proxy method end");

                return invoke;
            }
        });
        ITarget targetProxy = (ITarget) enhancer.create();
        // 4.执行方法
        targetProxy.method();
    }

    /**
     * <p>
     * 使用 JDK 创建代理对象
     * 使用 AspectJ 对目标对象进行调用
     * </p>
     *
     * @author Karl
     * @date 2023/2/23 13:51
     */
    @Test
    public void JDK_AspectJ_test() {
        // 1.创建目标对象
        Target target = new Target();

        // 2.创建代理对象。重点是 InvocationHandler 对象的初始化
        ITarget targetProxy = (ITarget) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 3.创建方法拦截器对象
                org.aopalliance.intercept.MethodInterceptor methodInterceptor = new org.aopalliance.intercept.MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                        // 4.定义代理逻辑
                        System.out.println("proxy method started");
                        Object invoke = methodInvocation.proceed();
                        System.out.println("proxy method end");
                        return invoke;
                    }
                };

                // 5.反射调用
                return methodInterceptor.invoke(new MethodInvocation() {
                    @Override
                    public Method getMethod() {
                        return method;
                    }

                    @Override
                    public Object[] getArguments() {
                        return args;
                    }

                    @Override
                    public Object proceed() throws Throwable {
                        return method.invoke(getThis(), getArguments());
                    }

                    @Override
                    public Object getThis() {
                        return target;
                    }

                    @Override
                    public AccessibleObject getStaticPart() {
                        return method;
                    }
                });
            }
        });

        // 6.执行方法
        targetProxy.method();
    }

    /**
     * <p>
     * 使用 Cglib 创建代理对象
     * 使用 AspectJ 对目标对象进行调用
     * </p>
     *
     * @author Karl
     * @date 2023/2/23 13:51
     */
    @Test
    public void Cglib_AspectJ_test() {
        // 1.创建目标对象
        Target target = new Target();

        // 2.创建代理对象，重点是 MethodInterceptor 对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setInterfaces(target.getClass().getInterfaces());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                // 3.创建方法拦截器对象
                org.aopalliance.intercept.MethodInterceptor methodInterceptor = new org.aopalliance.intercept.MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                        // 4.定义代理逻辑
                        System.out.println("proxy method started");
                        Object invoke = methodInvocation.proceed();
                        System.out.println("proxy method end");
                        return invoke;
                    }
                };

                // 5.反射调用
                return methodInterceptor.invoke(new MethodInvocation() {
                    @Override
                    public Method getMethod() {
                        return method;
                    }

                    @Override
                    public Object[] getArguments() {
                        return args;
                    }

                    @Override
                    public Object proceed() throws Throwable {
                        return method.invoke(getThis(), getArguments());
                    }

                    @Override
                    public Object getThis() {
                        return target;
                    }

                    @Override
                    public AccessibleObject getStaticPart() {
                        return method;
                    }
                });
            }
        });
        ITarget targetProxy = (ITarget) enhancer.create();
        // 6.执行方法
        targetProxy.method();
    }
}
