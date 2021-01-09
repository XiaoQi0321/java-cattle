package work.ambitlu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisConfig
 *
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 17:11
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"work.ambitlu.*.mapper","work.ambitlu.mapper"})
public class MyBatisConfig {
}
