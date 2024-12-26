package io.github.hongweihao.ss06.ioc.context;


/**
 * 上下文应用实现类
 * 用户入口类
 */
public class ClassPathXmlApplicationContext extends ApplicationContextBase {
    private final String[] configurations;

    public ClassPathXmlApplicationContext(String configuration) {
        this(new String[]{configuration});
    }

    public ClassPathXmlApplicationContext(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    protected String[] getConfigurations() {
        return this.configurations;
    }
}
