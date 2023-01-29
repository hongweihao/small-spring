package pri.hongweihao.smallspring.event;

import pri.hongweihao.smallspring.context.ApplicationListener;
import pri.hongweihao.smallspring.context.event.ContextRefreshedEvent;

public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("收到容器刷新事件" + event.getSource());
    }
}
