package pri.hongweihao.smallspring.beans.factory;

/**
 * bean名称感知接口
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}
