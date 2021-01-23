package work.ambitlu.core.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import work.ambitlu.core.user.AppConnect;
import work.ambitlu.core.user.ZlgUser;

/**
 * 用户详细信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:21
 */
public interface ZlgUserDetailsService extends UserDetailsService {

	/**
	 * 获取前端登陆的用户信息
	 * @param bizUserId openId
	 * @return UserDetails
	 */
	ZlgUser loadUserByAppIdAndBizUserId(String bizUserId);

	/**
	 * 如果必要的话，插入新增用户
	 * @param appConnect
	 */
	void insertUserIfNecessary(AppConnect appConnect);

}
