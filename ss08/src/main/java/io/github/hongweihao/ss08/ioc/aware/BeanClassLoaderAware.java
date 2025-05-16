package io.github.hongweihao.ss08.ioc.aware;

public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
