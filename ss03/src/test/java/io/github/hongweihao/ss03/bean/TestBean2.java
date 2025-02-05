package io.github.hongweihao.ss03.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestBean2 {
    private final String name;
    public TestBean2(String name) {
        this.name = name;
    }

    public TestBean2(Integer age) {
        this.name = "age";
    }

    public void test() {
        System.out.println("TestBean2 " + this.name);
    }

}
