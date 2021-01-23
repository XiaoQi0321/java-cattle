package work.ambitlu.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 登陆异常
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:42
 */
public class UnknownAccountException extends AuthenticationException {
	public UnknownAccountException(String msg) {
		super(msg);
	}
}
