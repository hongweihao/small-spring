package pri.hongweihao.smallspring.beans.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * 销毁对象
 */
public interface DisposableBean {
    void destroy() throws InvocationTargetException, IllegalAccessException;
}
