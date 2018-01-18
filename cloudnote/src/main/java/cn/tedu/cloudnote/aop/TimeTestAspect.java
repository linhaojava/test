package cn.tedu.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP切面组建 测试业务方法的性能
 * 
 * @author soft01
 *
 */
@Aspect
@Component
public class TimeTestAspect {

	/**
	 * 切入点表达式用于声明AOP组件对那些类，那些bean对象的方法进行拦截
	 * 有所三種切入點：1:Bean切入点--语法--bean(userService),bean(userService)||bean(
	 * noteService),bean(*service)
	 * 2:类切入点:语法：--within(具体类名)，within(cn.tedu.cloudnote.service.UserServiceImpl)
	 * 3:方法切入点
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	// @Around("bean(userService)")
	// @Around("bean(userService)||bean(noteService)") // 切入点表达式
	@Around("bean(*Service)")
	public Object test(ProceedingJoinPoint jp) throws Throwable {
		long t1 = System.currentTimeMillis();
		Object val = jp.proceed();// 调用目标方法
		// jp可以获取目标业务方法的“方法签名(Signature)”
		Signature s = jp.getSignature();
		System.out.println(s + "用时:" + (System.currentTimeMillis() - t1));
		return val;
	}
}
