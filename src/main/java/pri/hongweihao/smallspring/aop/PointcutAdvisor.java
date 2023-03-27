package pri.hongweihao.smallspring.aop;

public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
