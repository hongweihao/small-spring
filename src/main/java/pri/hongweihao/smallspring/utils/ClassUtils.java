package pri.hongweihao.smallspring.utils;

/**
 * <p>
 * ClassUtils
 * </p>
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            return ClassUtils.class.getClassLoader();
        }
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }

}
