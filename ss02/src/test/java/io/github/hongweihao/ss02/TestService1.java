package io.github.hongweihao.ss02;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestService1 {

    public String field1 = "v1";
    private String field2 = "v2";

    private static final String field3 = "field3";

    public void test() {
        System.out.println("TestService1");
    }

    private void pri() {
        System.out.println("pri");
    }

    protected static void pro() {
        System.out.println("pro");
    }

}
