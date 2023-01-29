package pri.hongweihao.smallspring.context.event;

import pri.hongweihao.smallspring.beans.BeansException;
import pri.hongweihao.smallspring.context.ApplicationEvent;
import pri.hongweihao.smallspring.context.ApplicationListener;
import pri.hongweihao.smallspring.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster/*, BeanFactoryAware*/ {
    private final Set<ApplicationListener<ApplicationEvent>> listeners = new LinkedHashSet<>();

    //private BeanFactory beanFactory;

    @Override
    public void addListener(ApplicationListener<ApplicationEvent> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ApplicationListener<ApplicationEvent> listener) {
        listeners.remove(listener);
    }

    protected Collection<ApplicationListener<ApplicationEvent>> getApplicationListeners(ApplicationEvent event) {
        return listeners.stream()
                .filter(listener -> supportEvent(listener, event))
                .collect(Collectors.toList());
    }

    private boolean supportEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }

    /*@Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }*/
}
