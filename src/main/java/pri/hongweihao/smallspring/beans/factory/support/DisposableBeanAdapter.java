package pri.hongweihao.smallspring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.beans.factory.DisposableBean;
import pri.hongweihao.smallspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 销毁对象接口的适配器对象
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final String beanName;
    private final Object bean;
    private final BeanDefinition beanDefinition;

    public DisposableBeanAdapter(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.beanDefinition = beanDefinition;
    }

    @Override
    public void destroy() throws InvocationTargetException, IllegalAccessException {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();

            // 如果XML配置的方法名是 destroy，则无需重复执行
            if ("destroy".equals(beanDefinition.getDestroyMethodName())) {
                return;
            }
        }

        if (StrUtil.isNotBlank(beanDefinition.getDestroyMethodName())) {
            Method method;
            try {
                method = bean.getClass().getMethod(beanDefinition.getDestroyMethodName());
            } catch (NoSuchMethodException e) {
                throw new BeansException("Could not find method " + beanDefinition.getDestroyMethodName() + " in bean " + beanName);
            }
            method.invoke(bean);
        }
    }
}
