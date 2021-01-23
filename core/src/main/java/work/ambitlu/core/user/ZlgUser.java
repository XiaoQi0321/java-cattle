package work.ambitlu.core.user;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:52
 */
@Getter
@Setter
public class ZlgUser extends User {
    /**
     * 用户ID
     */
    private Long userId;

    private String bizUserId;

    private String name;


    public ZlgUser(Long userId, String bizUserId, boolean enabled) {
        super(bizUserId, "", enabled,true, true, true , Collections.emptyList());
        this.userId = userId;
        this.bizUserId = bizUserId;
    }

}
