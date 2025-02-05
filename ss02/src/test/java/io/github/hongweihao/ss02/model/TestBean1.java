package io.github.hongweihao.ss02.model;

/**
 * <p>
 * TestBean1
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class TestBean1 {

    public String field1 = "v1";
    private String field2 = "v2";

    private static final String field3 = "field3";

    public void test() {
        System.out.println("TestBean1.test()");
    }

    private void pri() {
        System.out.println("pri");
    }

    protected static void pro() {
        System.out.println("pro");
    }

}
