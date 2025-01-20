package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class YY extends Y {

    private String description;

    public void yy() {
        System.out.println("-YY");
        System.out.println("Y name: " + name);
        x.x();
        System.out.println("YY description: " + description);
        System.out.println("YY-");
    }
}
