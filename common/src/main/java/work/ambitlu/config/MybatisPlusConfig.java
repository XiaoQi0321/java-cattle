package work.ambitlu.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 20:40
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public IKeyGenerator keyGenerator() {
		return new H2KeyGenerator();
	}

}
