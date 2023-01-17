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

}
