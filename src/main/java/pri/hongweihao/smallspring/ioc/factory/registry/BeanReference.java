package pri.hongweihao.smallspring.ioc.factory.registry;

/**
 * <p>
 * BeanFactory 管理的对象
 * </p>
 *
 * @date 2022/11/1 13:54
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
