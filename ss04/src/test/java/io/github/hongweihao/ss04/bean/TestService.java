package io.github.hongweihao.ss04.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestService {
    private final String name;
    public TestService(String name) {
        this.name = name;
    }

    public TestService(Integer age) {
        this.name = "age";
    }

    public void test() {
        System.out.println("test  -> " + this.name);
    }

}
