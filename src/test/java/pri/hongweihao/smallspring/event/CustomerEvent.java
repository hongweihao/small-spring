package pri.hongweihao.smallspring.event;


import pri.hongweihao.smallspring.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent {

    private final String name;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomerEvent(Object source, String name) {
        super(source);
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
