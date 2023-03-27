package pri.hongweihao.smallspring.aop.spring;

import pri.hongweihao.smallspring.aop.MethodBeforeAdvise;

import java.lang.reflect.Method;

/**
 * <p>
 * MyMethodBeforeAdvise 自定义切面
 * </p>
 *
 * @author Karl
 * @date 2023/3/23 18:51
 */
public class MyMethodBeforeAdvise implements MethodBeforeAdvise {

    @Override
    public void before(Object proxy, Method method, Object[] args) {
        System.out.println("MyMethodBeforeAdvise before.....");
    }
}
