package pri.hongweihao.smallspring.context.event;


import pri.hongweihao.smallspring.context.ApplicationEvent;
import pri.hongweihao.smallspring.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addListener(ApplicationListener<ApplicationEvent> listener);

    void removeListener(ApplicationListener<ApplicationEvent> listener);

    void multicastEvent(ApplicationEvent event);
}
