# FactoryBean

为接口提供隐藏的实现

改动点：

1. Bean配置: 单例/原型

2. FactoryBean 接口，应该提供默认的实现

3. 整合单例registry，应该继承默认的registry实现

4. 整合工厂，通过 BeanPostProcessor 融入。实例化Bean时，如果需要实例化的对象是BeanFactory类型，则通过BeanFactory提供的getObject获取实例


