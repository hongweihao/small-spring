package pri.hongweihao.smallspring.event;

import pri.hongweihao.smallspring.context.ApplicationListener;
import pri.hongweihao.smallspring.context.event.ContextClosedEvent;

public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("收到容器关闭事件" + event.getSource());
    }
}
