package work.ambitlu.core.security.token;

import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 二维码Token
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:29
 */
@NoArgsConstructor
public class MpAuthenticationToken extends MyAuthenticationToken {


	public MpAuthenticationToken(UserDetails principal, Object credentials) {
		super(principal, credentials, principal.getAuthorities());
	}
}
