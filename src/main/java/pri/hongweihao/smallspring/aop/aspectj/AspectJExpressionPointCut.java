package pri.hongweihao.smallspring.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import pri.hongweihao.smallspring.aop.ClassFilter;
import pri.hongweihao.smallspring.aop.MethodMatcher;
import pri.hongweihao.smallspring.aop.PointCut;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


public class AspectJExpressionPointCut implements ClassFilter, MethodMatcher, PointCut {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();
    private final PointcutExpression expression;

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    public AspectJExpressionPointCut(String expression) {
        PointcutParser parser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        this.expression = parser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return expression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> clazz) {
        return expression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
