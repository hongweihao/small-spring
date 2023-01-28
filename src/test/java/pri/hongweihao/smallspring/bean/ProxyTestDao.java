package pri.hongweihao.smallspring.bean;

import pri.hongweihao.smallspring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTestDao implements FactoryBean<TestDao> {
    @Override
    public TestDao getObject() {
        InvocationHandler handler = (proxy, method, args) -> "代理方法";
        return (TestDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{TestDao.class}, handler);
    }

    @Override
    public Class<TestDao> getObjectType() {
        return TestDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
