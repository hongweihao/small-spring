package pri.hongweihao.smallspring.aop;


public interface Pointcut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
