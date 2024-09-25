package pri.hongweihao.smallspring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {

    /*=================获取类对象=================*/
    /**
     * 获取类对象：通过全限定名字符串获取类对象
     */
    @Test
    public void get_class_test1() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("pri.hongweihao.smallspring.TestService");
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取类对象：通过类.class获取类对象
     */
    @Test
    public void get_class_test2() {
        Class<TestService> clazz = TestService.class;
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取类对象：通过实例.getClass()获取类对象
     */
    @Test
    public void get_class_test3() {
        TestService testService = new TestService();
        Class<? extends TestService> clazz = testService.getClass();
        Assert.assertNotNull(clazz);
    }

    /*=================创建实例=================*/

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService定义了一个无参构造方法
     */
    @Test
    public void create_instance1_test1() throws InstantiationException, IllegalAccessException {
        Class<TestService> clazz = TestService.class;
        TestService testService = clazz.newInstance();
        Assert.assertNotNull(testService);
        testService.test();
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService1 没有定义构造方法
     */
    @Test
    public void create_instance1_test2() throws InstantiationException, IllegalAccessException {
        Class<TestService1> clazz = TestService1.class;
        TestService1 testService1 = clazz.newInstance();
        Assert.assertNotNull(testService1);
        testService1.test();
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService2定义了有参构造方法
     */
    @Test
    public void create_instance1_test3() {
        Class<TestService2> clazz = TestService2.class;
        Assert.assertThrows(InstantiationException.class, clazz::newInstance);
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService3定义了有参构造方法和无参构造方法
     */
    @Test
    public void create_instance1_test4() throws InstantiationException, IllegalAccessException {
        Class<TestService3> clazz = TestService3.class;
        TestService3 testService3 = clazz.newInstance();
        Assert.assertNotNull(testService3);
        testService3.test();
    }

    /**
     * 初始化实例：获取构造方法来初始化实例
     * TestService4定义了共有有参构造方法和私有无参构造方法
     */
    @Test
    public void create_instance2_test() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<TestService4> clazz = TestService4.class;
        // 获取公共的构造方法
        Constructor<?>[] constructors = clazz.getConstructors();
        TestService4 testService4 = (TestService4) constructors[0].newInstance("fake");
        Assert.assertNotNull(testService4);
        testService4.test();


        // 获取所有的构造方法
        Constructor<TestService4> declaredConstructor = clazz.getDeclaredConstructor(Integer.class);
//        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        TestService4 testService44 = (TestService4) declaredConstructor.newInstance(1);
        Assert.assertNotNull(testService44);
        testService44.test();


    }




}
