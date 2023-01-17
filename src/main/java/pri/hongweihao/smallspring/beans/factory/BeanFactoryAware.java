package pri.hongweihao.smallspring.beans.factory;

/**
 * 工厂对象感知接口
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
