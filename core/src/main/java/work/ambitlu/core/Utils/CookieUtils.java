package work.ambitlu.core.Utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie
 *
 * @Date: 2021/1/27 11:42
 * @Author: Ambi 赵帅
 */
public class CookieUtils {
    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    public CookieUtils() {
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, (String)null, value, -1, false);
    }

    public static void addCookieEnableSecure(HttpServletResponse response, String name, String value) {
        addCookie(response, name, (String)null, value, -1, true);
    }

    public static void addCookie(HttpServletResponse response, String name, String domain, String value) {
        addCookie(response, name, domain, value, -1, false);
    }

    public static void addCookie(HttpServletResponse response, String name, String domain, String value, int maxAge, boolean secure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }

        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }

        cookie.setHttpOnly(true);
        if (secure) {
            cookie.setSecure(true);
        }

        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = getCookieMap(request);
        return (Cookie)cookieMap.get(name);
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setValue((String)null);
            response.addCookie(cookie);
            logger.info("Clear cookie: {}", name);
        }

    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return cookie == null ? null : cookie.getValue();
    }

    private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }
}
