package pri.hongweihao.smallspring;

/**
 * <p>
 * Bean定义对象
 * </p>
 *
 * @author Karl
 * @date 2022/10/25 13:29
 */
public class BeanDefinition {
    private final Class beanClass;


    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return this.beanClass;
    }


}
