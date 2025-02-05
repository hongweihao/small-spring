package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestSubBean2 extends TestBean2 {

    private String description;

    public void test() {
        System.out.println("TestSubBean2+++");
        System.out.println("TestBean2 name: " + name);
        testBean.test();
        System.out.println("TestSubBean2 description: " + description);
        System.out.println("TestSubBean2---");
    }
}
