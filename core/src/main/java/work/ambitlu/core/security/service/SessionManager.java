package work.ambitlu.core.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不采用session时
 * 运用Cookie方式验证
 *
 * @Date: 2021/1/27 15:56
 * @Author: Ambi 赵帅
 */
public interface SessionManager {

    /**
     * Stores the Session's ID, usually as a Cookie, to associate with future
     */
    void save(HttpServletRequest request, HttpServletResponse response, Authentication token);

    /**
     * load UserInfo from session
     */
    Authentication load(HttpServletRequest request);

}
