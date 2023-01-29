package pri.hongweihao.smallspring.context.event;

import pri.hongweihao.smallspring.context.ApplicationEvent;
import pri.hongweihao.smallspring.context.ApplicationListener;

import java.util.Collection;


public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Override
    public void multicastEvent(ApplicationEvent event) {
        Collection<ApplicationListener<ApplicationEvent>> applicationListeners = getApplicationListeners(event);
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            listener.onApplicationEvent(event);
        }
    }
}
