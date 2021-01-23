package work.ambitlu.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import work.ambitlu.core.security.token.MyAuthenticationToken;

/**
 * 微信小程序token
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:29
 */
@NoArgsConstructor
public class MiniAppAuthenticationToken extends MyAuthenticationToken {


	public MiniAppAuthenticationToken(UserDetails principal, Object credentials) {
		super(principal, credentials, principal.getAuthorities());
	}
}
