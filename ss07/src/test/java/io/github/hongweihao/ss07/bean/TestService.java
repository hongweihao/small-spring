package io.github.hongweihao.ss07.bean;

/**
 * <p>
 * TestService
 * </p>
 */
public class TestService {
    private String name;

    private Integer age;

    private TestDao testDao;

   /* public TestService(String name, Integer age, TestDao testDao) {
        this.name = name;
        this.age = age;
        this.testDao = testDao;
    }*/

    public void test() {
        System.out.println("testService.name: " + this.name);
        System.out.println("testService.age: " + this.age);
        this.testDao.test();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
