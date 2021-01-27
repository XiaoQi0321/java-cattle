package work.ambitlu.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SpringContextHolder工具类
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicationContext.
 * @Lazy(false) 为false会在容器初始化的时候急切的加载
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:38
 */
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicationContext.
	 */
	private static ApplicationContext context;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.context = applicationContext;
	}

	/**
	 * 获取applicationContext
	 * @return
	 */
	public ApplicationContext getApplicationContext() {
		return context;
	}


	/**
	 * 通过class获取Bean.
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> Map<String, T> getBeans(Class<T> clazz){
		assertContextInjected();
		return context.getBeansOfType(clazz);
	}

	/**
	 * 通过class获取Bean.
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
		assertContextInjected();
		return context.getBean(clazz);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) context.getBean(name);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * @param name
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz){
		assertContextInjected();
		return context.getBean(name, clazz);
	}

	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}
	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + context);
		}
		context = null;
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if(context == null) {
			throw new IllegalStateException("applicationContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
		}
	}

}
