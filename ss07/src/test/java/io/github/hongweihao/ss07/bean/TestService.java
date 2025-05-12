package io.github.hongweihao.ss07.bean;

import io.github.hongweihao.ss07.ioc.factory.extend.DisposableBean;
import io.github.hongweihao.ss07.ioc.factory.extend.InitializingBean;

/**
 * <p>
 * TestService
 * </p>
 */
public class TestService implements InitializingBean, DisposableBean {
    private String name;

    private Integer age;

    private TestDao testDao;

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
    public void destroy() {
        System.out.println("TestService destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("TestService afterPropertiesSet");
    }
}
