package pri.hongweihao.smallspring.context;

/**
 * 应用上下文核心接口，定义容器刷新方法
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh();

    void registerShutdownHook();

    void close();
}
