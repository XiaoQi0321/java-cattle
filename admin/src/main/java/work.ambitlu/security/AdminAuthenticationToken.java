package work.ambitlu.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import work.ambitlu.core.security.token.MyAuthenticationToken;

/**
 * 微信小程序token
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:29
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminAuthenticationToken extends MyAuthenticationToken {

	private String sessionUUID;

	private String imageCode;
	private String email;
	private String password;

	public AdminAuthenticationToken(UserDetails principal, Object credentials) {
		super(principal, credentials, principal.getAuthorities());
	}


}