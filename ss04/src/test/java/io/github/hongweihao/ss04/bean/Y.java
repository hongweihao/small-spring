package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class Y {

    private String name;
    private X x;

    public void y() {
        System.out.println("-YYY");
        System.out.println("Y name: " + name);
        x.x();
        System.out.println("YYY-");
    }
}
