package pri.hongweihao.smallspring.context;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
