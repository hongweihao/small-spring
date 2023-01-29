package pri.hongweihao.smallspring.event;


import pri.hongweihao.smallspring.context.ApplicationListener;

public class CustomerEventListener implements ApplicationListener<CustomerEvent> {

    @Override
    public void onApplicationEvent(CustomerEvent event) {
        System.out.println("接收到自定义事件" + event.getName());
    }
}
