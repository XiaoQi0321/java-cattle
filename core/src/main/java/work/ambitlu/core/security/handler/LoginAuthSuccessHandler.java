package work.ambitlu.core.security.handler;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import work.ambitlu.core.SpringContextHolder;
import work.ambitlu.core.entity.GenericResponse;
import work.ambitlu.core.entity.ServiceError;
import work.ambitlu.core.security.service.SessionManager;
import work.ambitlu.core.security.token.MyAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功，返回oauth token
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 23:46
 */
@Component
@Slf4j
public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private SessionManager sessionManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Object principal = authentication.getPrincipal();
		log.info("获取token 成功：{}", principal);

		sessionManager.save(request,response,authentication);

		response.setCharacterEncoding(CharsetUtil.UTF_8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.OK.value());
		response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.NORMAL,((MyAuthenticationToken) authentication).getToken())));
	}
}
