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

    protected String name;
    protected X x;

    public void y() {
        System.out.println("-Y");
        System.out.println("Y name: " + name);
        x.x();
        System.out.println("Y-");
    }
}
