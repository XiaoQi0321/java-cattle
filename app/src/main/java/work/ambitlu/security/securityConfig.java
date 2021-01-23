package work.ambitlu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import work.ambitlu.core.security.config.WebSecurityConfig;

/**
 * securityConfig
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 23:50
 */
@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfig {

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico")
				// 给 swagger 放行；不需要权限能访问的资源
				.antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/api/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/p/**").authenticated()
				.and().authorizeRequests().anyRequest().permitAll();
	}

}
