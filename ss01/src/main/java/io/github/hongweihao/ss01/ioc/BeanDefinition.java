package io.github.hongweihao.ss01.ioc;

/**
 * <p>
 * Bean定义对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:29
 */
public class BeanDefinition {
    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return this.bean;
    }


}
