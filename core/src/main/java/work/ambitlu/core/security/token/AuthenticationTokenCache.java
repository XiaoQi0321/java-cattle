package work.ambitlu.core.security.token;

import org.springframework.security.core.Authentication;
import work.ambitlu.core.cache.AbstractCacheService;

/**
 * 登陆对象
 * 缓存
 *
 * @author Ambi 赵帅
 * @date 2021/2/6 15:55
 */
public class AuthenticationTokenCache extends AbstractCacheService<Authentication> {


	private static final String CACHEKEY = "Auth:";

	public void addToken(String sessionId, Authentication token) {
		super.set(CACHEKEY + sessionId, token);
	}

	public Authentication getToken(String sessionId) {
		return super.get(CACHEKEY + sessionId, Authentication.class);
	}

}
