package com.miller.springframework.aop.aspectj;

import com.miller.springframework.aop.Pointcut;
import com.miller.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author miller
 * @version 1.0.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    //切面
    private AspectJExpressionPointcut pointcut;
    //具体的拦截方法
    private Advice advice;
    //表达式
    private String expression;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
