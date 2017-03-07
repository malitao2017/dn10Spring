/*
 * OptLogger.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 需要增加的日志切面管理
 * @author LT
 * @version 1.0, 2015年10月29日
 */
@Component("optLogger")
@Aspect
public class OptLogger {
	//两种方式等价
	@Pointcut("within(aop.annotation..*)")
//	@Pointcut("execution(* aop.annotation.*.*(..))")
	public void aopPoint(){};
	
	@Before("aopPoint()")
	public void loggerBefore(){
		System.out.println("-前置通知-");
	}
	
	@AfterReturning(pointcut="aopPoint()",returning="reVal")
	public void loggerAfterReturn(Object reVal){
		System.out.println("-后置通知：执行正常-"+reVal);
	}
	
	@AfterThrowing(pointcut="aopPoint()",throwing="ex")
	public void loggerAfterThrow(Exception ex){
		System.out.println("-后置通知：执行问题-:"+ex.getStackTrace()[0]);
	}
	@After("aopPoint()")
	public void loggerAfter(){
		System.out.println("-最终通知-");
	}
	/**
	 * 只有标签 <aop:around才可以使用
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("aopPoint()")
	public Object loggerAround(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = pjp.proceed();
		String nameClass = pjp.getTarget().getClass().getName();
		String nameMethod = pjp.getSignature().getName();
		System.out.println("调用了类："+nameClass+" 方法："+nameMethod);
		return obj;
	}
	
}
