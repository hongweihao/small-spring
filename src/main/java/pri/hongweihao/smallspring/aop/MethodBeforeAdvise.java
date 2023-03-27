package pri.hongweihao.smallspring.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvise extends BeforeAdvise {
    void before(Object proxy, Method method, Object[] args);
}
