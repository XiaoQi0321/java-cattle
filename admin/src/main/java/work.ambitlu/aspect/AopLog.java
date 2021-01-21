package work.ambitlu.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些声明信息
 *
 * @Date: 2021/1/21 14:29
 * @Author: Ambi 赵帅
 */
@Aspect
@Slf4j
@Component
public class AopLog {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	//线程局部的变量,解决多线程中相同变量的访问冲突问题。
	ThreadLocal<Long> startTime = new ThreadLocal<>();


	//统一切点,对work.ambitlu.controller及其子包中所有的类的所有方法切面
	@Pointcut("execution(public * work.ambitlu.controller..*.*(..))")
	public void aopWebLog(){

	}

	@Before("aopWebLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP方法 : " + request.getMethod());
		logger.info("IP地址 : " + request.getRemoteAddr());
		logger.info("类的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		//logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("参数 : " + request.getQueryString());
	}

	@AfterReturning(pointcut = "aopWebLog()",returning = "retObject")
	public void doAfterReturning(Object retObject) throws Throwable {
		// 处理完请求，返回内容
		logger.info("应答值 : " + retObject);
		logger.info("费时: " + (System.currentTimeMillis() - startTime.get()));
	}

	//抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
	@AfterThrowing(pointcut = "aopWebLog()", throwing = "ex")
	public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
		logger.error("执行 " + " 异常:{}", ex.getMessage());
	}

}

