package pri.hongweihao.smallspring.beans;

/**
 * <p>
 * 工厂抛出的异常对象
 * </p>
 *
 * @date 2022/10/26 13:54
 */
public class BeanException extends RuntimeException {
    public BeanException(String message) {
        super(message);
    }
}
