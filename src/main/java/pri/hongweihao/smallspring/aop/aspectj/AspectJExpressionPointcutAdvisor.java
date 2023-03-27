package pri.hongweihao.smallspring.aop.aspectj;

import org.aopalliance.aop.Advice;
import pri.hongweihao.smallspring.aop.Pointcut;
import pri.hongweihao.smallspring.aop.PointcutAdvisor;

/**
 * <p>
 * 访问者实现类，主要负责创建切点对象
 * </p>
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private String expression;
    private Pointcut pointcut;
    private Advice advice;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            this.pointcut = new AspectJExpressionPointcut(this.expression);
        }
        return this.pointcut;
    }
}
