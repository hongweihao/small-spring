package pri.hongweihao.smallspring.bean;

import pri.hongweihao.smallspring.beans.factory.DisposableBean;
import pri.hongweihao.smallspring.beans.factory.InitializingBean;

/**
 * <p>
 * TestService
 * </p>
 */
public class TestService implements InitializingBean, DisposableBean {
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

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行初始化方法 afterPropertiesSet");
    }

    public void afterPropertiesSet2() {
        System.out.println("执行初始化方法 afterPropertiesSet2");
    }

    @Override
    public void destroy() {
        System.out.println("执行销毁方法 destroy");
    }

    public void destroy2() {
        System.out.println("执行销毁方法 destroy2");
    }

}
