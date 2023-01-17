package pri.hongweihao.smallspring.context;

import pri.hongweihao.smallspring.beans.factory.Aware;

/**
 * 上下文对象感知接口
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext);
}
