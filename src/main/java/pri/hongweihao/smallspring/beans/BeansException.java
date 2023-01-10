package pri.hongweihao.smallspring.beans;

/**
 * <p>
 * 工厂抛出的异常对象
 * </p>
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }
}
