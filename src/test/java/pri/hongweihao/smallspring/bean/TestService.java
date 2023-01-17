package pri.hongweihao.smallspring.bean;

import pri.hongweihao.smallspring.beans.factory.*;
import pri.hongweihao.smallspring.context.ApplicationContext;
import pri.hongweihao.smallspring.context.ApplicationContextAware;

/**
 * <p>
 * TestService
 * </p>
 */
public class TestService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware {
    private String name;

    private Integer age;

    private TestDao testDao;


    private BeanFactory beanFactory;
    private ApplicationContext context;
    private ClassLoader classLoader;
    private String beanName;


    public void test() {
        System.out.println("testService.name: " + this.name);
        System.out.println("testService.age: " + this.age);
        this.testDao.test();

        System.out.println("testService.factory: " + this.beanFactory);
        System.out.println("testService.context: " + this.context);
        System.out.println("testService.classLoader: " + this.classLoader);
        System.out.println("testService.beanName: " + this.beanName);
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

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.classLoader = beanClassLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName=beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }
}
