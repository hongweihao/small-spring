# 扩展接口

BeanFactoryPostProcessor: 加载完BeanDefinition后执行 ，需要手动调用执行

InitializingBean: 由 Bean 类实现，对 bean 填充属性后调用执行，整合在 BeanFactory 流程中。

DisposableBean: 由 Bean 类实现，注册到 Runtime 的关闭回调方法中，当接收到 Runtime 关闭回调时，将单例注册中心中的 Bean 对象全部获取出来，执行 destroy 方法。

```java
Runtime.getRuntime().addShutdownHook(new Thread(this::close));
```

BeanPostProcessor: 包含两个方法。`postProcessBeforeInitialization` 在 `InitializingBean` 执行前执行，`postProcessAfterInitialization` 在`InitializingBean` 执行后执行。



