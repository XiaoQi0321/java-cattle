package work.ambitlu.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import work.ambitlu.core.security.exception.UnknownAccountException;
import work.ambitlu.core.security.service.ZlgUserDetailsService;
import work.ambitlu.core.user.AppConnect;
import work.ambitlu.core.user.ZlgUser;
import work.ambitlu.utils.Json;
import work.ambitlu.utils.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 微信小程序登陆
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:19
 */
@Component
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final PasswordEncoder passwordEncoder;
	private final ZlgUserDetailsService zlgUserDetailsService;

	protected LoginAuthenticationFilter(ZlgUserDetailsService zlgUserDetailsService, PasswordEncoder passwordEncoder) {
		super("/login");
		this.passwordEncoder = passwordEncoder;
		this.zlgUserDetailsService = zlgUserDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!ServletUtil.METHOD_POST.equals(request.getMethod())) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		String requestBody = getStringFromStream(request);

		if (StrUtil.isBlank(requestBody)) {
			throw new AuthenticationServiceException("无法获取输入信息");
		}
		AdminAuthenticationToken adminAuthenticationToken  =  Json.parseObject(requestBody, AdminAuthenticationToken.class);


		String username = adminAuthenticationToken.getPrincipal() == null?"NONE_PROVIDED":adminAuthenticationToken.getName();


		String kaptchaKey = "imageCode" + adminAuthenticationToken.getSessionUUID();

		String kaptcha = RedisUtil.get(kaptchaKey);

		RedisUtil.del(kaptchaKey);

		if(StrUtil.isBlank(adminAuthenticationToken.getImageCode()) || !adminAuthenticationToken.getImageCode().equalsIgnoreCase(kaptcha)){
			throw new RuntimeException("验证码有误");
		}

		UserDetails user;
		try {
			user = zlgUserDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException var6) {
			throw new UsernameNotFoundException("账号或密码不正确");
		}

		String encodedPassword = user.getPassword();
		String rawPassword = adminAuthenticationToken.getCredentials().toString();

		// 密码不正确
		if (!passwordEncoder.matches(rawPassword,encodedPassword)){
			throw new UsernameNotFoundException("账号或密码不正确");
		}

		if (!user.isEnabled()) {
			throw new UsernameNotFoundException("账号已被锁定,请联系管理员");
		}
		AdminAuthenticationToken result = new AdminAuthenticationToken(user, adminAuthenticationToken.getCredentials());
		result.setDetails(adminAuthenticationToken.getDetails());
		return result;
	}

	private String getStringFromStream(HttpServletRequest req) {
		ServletInputStream is;
		try {
			is = req.getInputStream();
			int nRead = 1;
			int nTotalRead = 0;
			byte[] bytes = new byte[10240];
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0) {
					nTotalRead = nTotalRead + nRead;
				}
			}
			return new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	@Autowired
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		super.setAuthenticationSuccessHandler(successHandler);
	}

	@Override
	@Autowired
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(failureHandler);
	}
}
