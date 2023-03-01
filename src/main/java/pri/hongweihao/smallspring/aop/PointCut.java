package pri.hongweihao.smallspring.aop;


public interface PointCut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
