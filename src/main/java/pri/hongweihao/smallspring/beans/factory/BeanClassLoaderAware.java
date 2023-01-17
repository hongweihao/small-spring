package pri.hongweihao.smallspring.beans.factory;

/**
 * 类加载对象感知接口
 */
public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader beanClassLoader);
}
