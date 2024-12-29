package io.github.hongweihao.ss07.ioc.context;


/**
 * 上下文应用实现类
 * 用户入口类
 */
public class ApplicationContextClassPathXmlImpl extends ApplicationContextBase {
    private final String[] configurations;

    public ApplicationContextClassPathXmlImpl(String configuration) {
        this(new String[]{configuration});
    }

    public ApplicationContextClassPathXmlImpl(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    protected String[] getConfigurations() {
        return this.configurations;
    }
}
