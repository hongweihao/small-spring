package io.github.hongweihao.ss02;

import io.github.hongweihao.ss02.model.TestBean1;
import io.github.hongweihao.ss02.model.TestBean2;
import io.github.hongweihao.ss02.model.TestBean3;
import io.github.hongweihao.ss02.model.TestBean4;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;

public class ReflectionTest {

    /*=================获取Class对象=================*/

    /**
     * 获取Class对象：通过全限定名字符串获取Class对象
     */
    @Test
    public void get_class_test1() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("io.github.hongweihao.ss02.model.TestBean1");
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取Class对象：通过类.class获取Class对象
     */
    @Test
    public void get_class_test2() {
        Class<TestBean1> clazz = TestBean1.class;
        Assert.assertNotNull(clazz);
    }

    /**
     * 获取Class对象：通过实例.getClass()获取Class对象
     */
    @Test
    public void get_class_test3() {
        TestBean1 testService = new TestBean1();
        Class<? extends TestBean1> clazz = testService.getClass();
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
        Class<TestBean1> clazz = TestBean1.class;

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
        Assert.assertThrows(NoSuchFieldException.class, () -> clazz.getField("field2"));

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
        Class<TestBean1> clazz = TestBean1.class;

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
        Class<TestBean1> clazz = TestBean1.class;
        TestBean1 testService = clazz.newInstance();
        Assert.assertNotNull(testService);
        testService.test();
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService1 没有定义构造方法
     */
    @Test
    public void create_instance1_test2() throws InstantiationException, IllegalAccessException {
        Class<TestBean1> clazz = TestBean1.class;
        TestBean1 testBean1 = clazz.newInstance();
        Assert.assertNotNull(testBean1);
        testBean1.test();
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService2定义了有参构造方法
     */
    @Test
    public void create_instance1_test3() {
        Class<TestBean2> clazz = TestBean2.class;
        Assert.assertThrows(InstantiationException.class, clazz::newInstance);
    }

    /**
     * 初始化实例：通过 Class.newInstance() 调用无参构造方法初始化
     * TestService3定义了有参构造方法和无参构造方法
     */
    @Test
    public void create_instance1_test4() throws InstantiationException, IllegalAccessException {
        Class<TestBean3> clazz = TestBean3.class;
        TestBean3 testBean3 = clazz.newInstance();
        Assert.assertNotNull(testBean3);
        testBean3.test();
    }

    /**
     * 初始化实例：获取构造方法来初始化实例
     * TestService4定义了共有有参构造方法和私有无参构造方法
     */
    @Test
    public void create_instance2_test() throws Exception {
        Class<TestBean4> clazz = TestBean4.class;
        // 获取公有的构造方法
        Constructor<?>[] constructors = clazz.getConstructors();
        TestBean4 testBean4 = (TestBean4) constructors[0].newInstance("fake");
        Assert.assertNotNull(testBean4);
        testBean4.test();

        // 获取所有的构造方法,调用私有构造方法会报错
        Constructor<TestBean4> declaredConstructor = clazz.getDeclaredConstructor(Integer.class);
        Assert.assertThrows(IllegalAccessException.class, () -> declaredConstructor.newInstance(1));
    }

    @Test
    public void direct_new_test() {
        TestBean1 testBean1 = new TestBean1();
        testBean1.test();
    }

    @Test
    public void reflection_new_test() throws Exception {
        reflection("io.github.hongweihao.ss02.model.TestBean1", "test");
    }

    private void reflection(String className, String methodName) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);

        // 获取构造方法，初始化实例
        Constructor<?>[] constructors = clazz.getConstructors();
        Object o = constructors[0].newInstance();

        // 获取方法对象并调用
        Method method = clazz.getMethod(methodName);
        method.invoke(o);
    }

}
