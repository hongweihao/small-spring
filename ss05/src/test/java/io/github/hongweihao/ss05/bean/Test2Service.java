package io.github.hongweihao.ss05.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:35
 */
public class Test2Service {

    private TestService testService;

    public void test() {
        System.out.print("test2  -> ");
        testService.test();
    }

}
