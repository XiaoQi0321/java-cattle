package work.ambitlu.core.security.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import work.ambitlu.core.entity.GenericResponse;
import work.ambitlu.core.entity.ServiceError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常
 *
 * @author Ambi 赵帅
 * @date 2021/1/24 0:03
 */
@Slf4j
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("content-type", "application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_NO_SIGN_IN)));
	}
}
