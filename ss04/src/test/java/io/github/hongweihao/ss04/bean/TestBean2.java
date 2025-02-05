package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestBean2 {

    protected String name;
    protected TestBean testBean;

    public void test() {
        System.out.println("TestBean2+++");
        System.out.println("TestBean2 name: " + name);
        testBean.test();
        System.out.println("TestBean2---");
    }
}
