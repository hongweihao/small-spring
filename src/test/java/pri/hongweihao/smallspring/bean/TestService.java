package pri.hongweihao.smallspring.bean;

/**
 * <p>
 * TestService
 * </p>
 *
 * @date 2022/10/25 13:35
 */
public class TestService {
    private final String name;

    private final TestDao testDao;

    public TestService(String name, TestDao testDao) {
        this.name = name;
        this.testDao = testDao;
    }

    public void test() {
        System.out.println("testService.name: " + this.name);
        this.testDao.test();
    }
}
