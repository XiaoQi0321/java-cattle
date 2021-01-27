package work.ambitlu.core.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import work.ambitlu.core.SpringContextHolder;
import work.ambitlu.core.Utils.CookieUtils;
import work.ambitlu.core.security.SecurityProperties;
import work.ambitlu.core.security.service.SessionManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt 身份校验
 *
 * @author Ambi 赵帅
 * @date 2021/1/25 23:43
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter implements Ordered {

	public static final int DEFAULT_ORDER = 1;

	@Autowired
	private SessionManager sessionManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication token = sessionManager.load(request);
		if (token != null ){
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		try {
			filterChain.doFilter(request, response);
		} finally {
			SecurityContextHolder.clearContext();
		}
	}

	@Override
	public int getOrder() {
		return DEFAULT_ORDER;
	}
}
