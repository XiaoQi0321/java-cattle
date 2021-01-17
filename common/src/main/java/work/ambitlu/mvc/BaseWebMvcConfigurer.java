package work.ambitlu.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2020/12/19 20:27
 */
@Configuration
public class BaseWebMvcConfigurer implements WebMvcConfigurer {


	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer
				.addPathPrefix("api",c -> c.isAnnotationPresent(ApiRestController.class))
				.addPathPrefix("admin",c -> c.isAnnotationPresent(ConsoleRestController.class));
	}

}
