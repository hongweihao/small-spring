package pri.hongweihao.smallspring.aop.framework;

import pri.hongweihao.smallspring.aop.AdviseSupport;

/**
 * <p>
 * 代理对象工厂
 * </p>
 */
public class ProxyFactory {
    private final AdviseSupport adviseSupport;

    public ProxyFactory(AdviseSupport adviseSupport) {
        this.adviseSupport = adviseSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        if (adviseSupport.isProxyTargetClass()) {
            return new CGlibAopProxy(adviseSupport);
        }
        return new JDKDynamicAopProxy(adviseSupport);
    }
}
