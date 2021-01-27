package work.ambitlu.core.security.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import work.ambitlu.core.SpringContextHolder;
import work.ambitlu.core.Utils.CookieUtils;
import work.ambitlu.core.security.SecurityProperties;

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

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, Authentication token) {
        String sessionId = UUID.randomUUID().toString();
        // 生成cookie信息
        addCookie(request, response, sessionId);
        // 添加到缓冲中
        RedisTemplate<String,Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");
        redisTemplate.opsForValue().set(sessionId,token);
    }

    @Override
    public Authentication load(HttpServletRequest request) {
        String sessionIdName = securityProperties.getSessionIdName();
        String sessionId = CookieUtils.getCookieValue(request, sessionIdName);
        // 添加到缓存中
        RedisTemplate<String,Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");
        Authentication authentication = (Authentication)redisTemplate.opsForValue().get(sessionId);
        // 更新缓存
        if (authentication != null){
            redisTemplate.opsForValue().set(sessionId,authentication);
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
