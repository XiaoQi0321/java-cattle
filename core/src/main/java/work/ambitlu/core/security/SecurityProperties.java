package work.ambitlu.core.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Security
 * 常用参数配置
 *
 * @Date: 2021/1/27 13:38
 * @Author: Ambi 赵帅
 */
@Getter
@Setter
public class SecurityProperties {

    private static final int DEFAULT_SESSION_TIMEOUT = 60 * 60;
    public static final String DEFAULT_SESSION_ID_NAME = "ZID";
    public static final String DEFAULT_ADMIN_SESSION_ID_NAME = "AID";

    private String sessionIdName = DEFAULT_SESSION_ID_NAME;

}
