//package cn.tedu.cloudnote.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
///**
// * Aspect:切面组建
// * 
// * @author soft01
// *
// */
//@Aspect
//@Component
//public class DemoAspect {
//	private long beforeTime;
//
//	 /**
//	 * 在userService 组件执行之前执行次切面方法
//	 */
//	 @Before("bean(userService)")
//	 public void test1() {
//	 System.out.println("hello java1");
//	 beforeTime = System.currentTimeMillis();
//	 }
//	
//	 /**
//	 * 在userService 组件返回之后执行次切面方法
//	 */
//	 @AfterReturning("bean(userService)")
//	 public void test2() {
//	 System.out.println("hello java2");
//	 System.out.println("效率" + (System.currentTimeMillis() - beforeTime));
//	 }
//	
//	 /**
//	 * 在userService 组件异常之后执行次切面方法
//	 */
//	 @AfterThrowing("bean(userService)")
//	 public void test3() {
//	 System.out.println("hello java3");
//	 System.out.println("效率" + (System.currentTimeMillis() - beforeTime));
//	 }
//	
//	 /**
//	 * 在userService 组件执行之后执行次切面方法
//	 */
//	 @After("bean(userService)")
//	 public void test4() {
//	 System.out.println("hello java4");
//	 System.out.println("效率" + (System.currentTimeMillis() - beforeTime));
//	 }
//
//	/**
//	 * 环绕通知 必须包含方法参数ProceedingJoinPoint.必须有返回值Object,必须抛出异常
//	 * 
//	 * @return
//	 */
//	@Around("bean(userService)")
//	public Object testAround(ProceedingJoinPoint jp) throws Throwable {
//		System.out.println("helle java");// 代表在调用业务方法之前增加的逻辑功能
//		Object val = jp.proceed();// 调用目标业务方法，
//		return val;// 并返回业务方法的返回值
//	}
//}
