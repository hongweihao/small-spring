package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestSubBean extends TestBean {
    private String description;

    public void test() {
        System.out.println("TestSubBean+++");
        System.out.println(name);
        System.out.println(description);
        System.out.println("TestSubBean---");
    }

}
