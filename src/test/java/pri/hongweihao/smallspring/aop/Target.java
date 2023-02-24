package pri.hongweihao.smallspring.aop;

/**
 * <p>
 * 被代理类
 * </p>
 *
 * @author Karl
 * @date 2023/2/23 13:20
 */
public class Target implements ITarget {

    @Override
    public void method() {
        System.out.println("target method");
    }

}
