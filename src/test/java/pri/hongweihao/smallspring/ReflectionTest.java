package pri.hongweihao.smallspring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionTest {

    /*=================获取Class对象=================*/

    /**
     * 获取Class对象：通过全限定名字符串获取Class对象
     */
    @Test
    public void get_class_test1() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("pri.hongweihao.smallspring.TestService1");
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取Class对象：通过类.class获取Class对象
     */
    @Test
    public void get_class_test2() {
        Class<TestService1> clazz = TestService1.class;
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取Class对象：通过实例.getClass()获取Class对象
     */
    @Test
    public void get_class_test3() {
        TestService1 testService = new TestService1();
        Class<? extends TestService1> clazz = testService.getClass();
        Assert.assertNotNull(clazz);
    }


    /*=====================获取类属性==================*/

    /**
     * <p>
     * 获取类属性
     * </p>
     */
    @Test
    public void test() throws NoSuchFieldException {
        Class<TestService1> clazz = TestService1.class;

        System.out.println("公有属性：");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("全部属性：");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("查询共有属性：");
        Field field = clazz.getField("field1");
        System.out.println(field);

        System.out.println("clazz.getField查询私有属性报错");
        Assert.assertThrows(NoSuchFieldException.class , () -> clazz.getField("field2"));

        System.out.println("查询全部属性：");
        Field declaredField1 = clazz.getDeclaredField("field1");
        System.out.println(declaredField1);

        Field declaredField2 = clazz.getDeclaredField("field2");
        System.out.println(declaredField2);


    }

    /*=====================获取类方法==================*/

    /**
     * 获取类方法
     */
    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestService1> clazz = TestService1.class;

        System.out.println("当前类以及父类的所有公有方法: ");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("\n当前类的所有方法: ");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
    }


    /*=================创建实例=================*/

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService定义了一个无参构造方法
     */
    @Test
    public void create_instance1_test1() throws InstantiationException, IllegalAccessException {
        Class<TestService1> clazz = TestService1.class;
        TestService1 testService = clazz.newInstance();
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
        // 获取公有的构造方法
        Constructor<?>[] constructors = clazz.getConstructors();
        TestService4 testService4 = (TestService4) constructors[0].newInstance("fake");
        Assert.assertNotNull(testService4);
        testService4.test();

        // 获取所有的构造方法,调用私有构造方法会报错
        Constructor<TestService4> declaredConstructor = clazz.getDeclaredConstructor(Integer.class);
        Assert.assertThrows(IllegalAccessException.class, () -> declaredConstructor.newInstance(1));
    }

    @Test
    public void direct_new_test() {
        TestService1 testService1 = new TestService1();
        testService1.test();
    }

    @Test
    public void reflection_new_test() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = Class.forName("pri.hongweihao.smallspring.TestService1");

        // 获取构造方法，初始化实例
        Constructor<?>[] constructors = clazz.getConstructors();
        Object o = constructors[0].newInstance();

        // 获取方法对象并调用
        Method method = clazz.getMethod("test");
        method.invoke(o);
    }


}
