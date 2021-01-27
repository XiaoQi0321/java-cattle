package work.ambitlu.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import work.ambitlu.core.security.exception.UnknownAccountException;
import work.ambitlu.core.security.service.ZlgUserDetailsService;
import work.ambitlu.core.user.AppConnect;
import work.ambitlu.core.user.ZlgUser;
import work.ambitlu.utils.Json;

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
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements Ordered {

	public static final int DEFAULT_ORDER = -1;

	private final WxMaService wxMaService;
	private final ZlgUserDetailsService zlgUserDetailsService;

	@Value("${spring.profiles.active}")
	private String profile;

	protected LoginAuthenticationFilter(WxMaService wxMaService, ZlgUserDetailsService zlgUserDetailsService) {
		super("/login");
		this.wxMaService = wxMaService;
		this.zlgUserDetailsService = zlgUserDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		if (!ServletUtil.METHOD_POST.equals(request.getMethod())) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		String requestBody = getStringFromStream(request);

		if (StrUtil.isBlank(requestBody)) {
			throw new AuthenticationServiceException("无法获取输入信息");
		}
		MiniAppAuthenticationToken authentication  =  Json.parseObject(requestBody, MiniAppAuthenticationToken.class);
		String code = String.valueOf(authentication.getPrincipal());
		ZlgUser loadedUser = null;

		WxMaJscode2SessionResult session = null;

		AppConnect appConnect = new AppConnect();
		try {

			if ("dev".equals(profile) && "1".equals(code)){
				session = new WxMaJscode2SessionResult();
				session.setOpenid("1");
			} else {
				session = wxMaService.getUserService().getSessionInfo(code);
			}

			loadedUser = zlgUserDetailsService.loadUserByAppIdAndBizUserId(session.getOpenid());
		} catch (UsernameNotFoundException | WxErrorException var6) {
			if (session == null) {
				throw new UnknownAccountException("无法获取用户登陆信息");
			}
			appConnect.setBizUserId(session.getOpenid());
			appConnect.setBizUnionid(session.getUnionid());
			zlgUserDetailsService.insertUserIfNecessary(appConnect);
		}

		if (loadedUser == null) {
			loadedUser = zlgUserDetailsService.loadUserByAppIdAndBizUserId(appConnect.getBizUserId());
		}
		MiniAppAuthenticationToken result = new MiniAppAuthenticationToken(loadedUser, authentication.getCredentials());
		result.setDetails(authentication.getDetails());
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

	@Override
	public int getOrder() {
		return DEFAULT_ORDER;
	}
}
