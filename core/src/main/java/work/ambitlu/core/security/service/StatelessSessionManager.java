package work.ambitlu.core.security.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import work.ambitlu.core.SpringContextHolder;
import work.ambitlu.core.Utils.CookieUtils;
import work.ambitlu.core.security.SecurityProperties;
import work.ambitlu.core.security.token.AuthenticationTokenCache;
import work.ambitlu.core.security.token.MyAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 运用Cookie方式验证
 *
 * @Date: 2021/1/27 15:56
 * @Author: Ambi 赵帅
 */
public class StatelessSessionManager implements SessionManager{

    private static final String HTTPS_NAME = "server.https";

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private Environment environment;
    @Autowired
    private AuthenticationTokenCache cache;

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, Authentication token) {
        String sessionId = UUID.randomUUID().toString();
        // 生成cookie信息
        addCookie(request, response, sessionId);
        // 添加到缓冲中
        cache.addToken(sessionId,token);
        ((MyAuthenticationToken) token).setToken(sessionId);
    }

    @Override
    public Authentication load(HttpServletRequest request) {
        String sessionIdName = securityProperties.getSessionIdName();
        String sessionId = CookieUtils.getCookieValue(request, sessionIdName);
        if(StringUtils.isBlank(sessionId)){
            return null;
        }

        Authentication authentication = cache.getToken(sessionId);
        // 更新缓存
        if (authentication != null){
            cache.set(sessionId,authentication);
        }
        return authentication;
    }

    // 生成cookie
    private void addCookie(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        String sessionIdName = securityProperties.getSessionIdName();
        if (StringUtils.isNotBlank(environment.getProperty(HTTPS_NAME))) {
            // 设置cookie Secure 使用ssl 加密
            CookieUtils.addCookieEnableSecure(response, sessionIdName, sessionId);
        } else {
            CookieUtils.addCookie(response, sessionIdName, sessionId);
        }
    }
}
