package pri.hongweihao.smallspring.beans.factory;


public interface FactoryBean<T> {

    T getObject();

    Class<T> getObjectType();

    boolean isSingleton();
}
