package io.github.hongweihao.ss03.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class Y {
    private final String name;
    public Y(String name) {
        this.name = name;
    }

    public Y(Integer age) {
        this.name = "age";
    }

    public void y() {
        System.out.println("Y " + this.name);
    }

}
