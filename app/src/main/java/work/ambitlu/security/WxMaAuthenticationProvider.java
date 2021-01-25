package work.ambitlu.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import work.ambitlu.core.security.provider.AbstractUserDetailsAuthenticationProvider;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:13
 */
@Component
public class WxMaAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Override
	protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
		System.out.println("123");
		return null;
	}

	@Override
	protected UserDetails retrieveUser(String username, Authentication authentication) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
