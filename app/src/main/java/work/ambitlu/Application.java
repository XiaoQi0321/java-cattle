package work.ambitlu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/7 19:03
 */
@SpringBootApplication
@MapperScan("work.ambitlu.*.mapper*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

}
