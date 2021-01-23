package work.ambitlu.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.ambitlu.annotation.RedisLock;
import work.ambitlu.core.security.service.ZlgUserDetailsService;
import work.ambitlu.core.user.AppConnect;
import work.ambitlu.core.user.ZlgUser;
import work.ambitlu.uzs.mapper.AppConnectMapper;
import work.ambitlu.uzs.mapper.UzsUserMapper;
import work.ambitlu.uzs.model.UzsUser;

import java.time.LocalDateTime;


/**
 * 系统用户登陆
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:24
 */
@Slf4j
@Service
@AllArgsConstructor
public class ZlgSysUserServiceImpl implements ZlgUserDetailsService {

	private final UzsUserMapper userMapper;
	private final AppConnectMapper appConnectMapper;


	/**
	 * 获取前端登陆的用户信息
	 * @param bizUserId openId
	 */
	@Override
	public ZlgUser loadUserByAppIdAndBizUserId(String bizUserId) {
		UzsUser user = userMapper.getUserByBizUserId(bizUserId);
		if (user == null) {
			throw new UsernameNotFoundException("无法获取用户信息");
		}
		ZlgUser zlgUser = new ZlgUser(user.getId(), bizUserId,!user.getDeleted());
		zlgUser.setName(user.getName());
		return zlgUser;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	@RedisLock(lockName = "insertUser", key = "#appConnect.appId + ':' + #appConnect.bizUserId")
	@Caching(evict = {
			@CacheEvict(cacheNames = "zlg_sys_user", key = "#appConnect.appId + ':' + #appConnect.bizUserId"),
			@CacheEvict(cacheNames = "AppConnect", key = "#appConnect.appId + ':' + #appConnect.bizUserId")
	})
	public void insertUserIfNecessary(AppConnect appConnect) {
		AppConnect dbAppConnect = appConnectMapper.selectOne(Wrappers.<AppConnect>lambdaQuery().eq(AppConnect::getBizUserId, appConnect.getBizUserId()));
		if(dbAppConnect != null) {
			return;
		}
		String bizUnionId = appConnect.getBizUnionid();
		Long userId = null;
		UzsUser user;
		if (StringUtils.isNotBlank(bizUnionId)){
			AppConnect connect = appConnectMapper.selectOne(Wrappers.<AppConnect>lambdaQuery().eq(AppConnect::getBizUnionid, bizUnionId));
			if (connect != null){
				userId = connect.getUserId();
			}
		}
		if (userId == null){
			user = new UzsUser();
			user.setCreateTime(LocalDateTime.now());
			user.setNickName(EmojiUtil.toAlias(StrUtil.isBlank(appConnect.getNickName()) ? "" : appConnect.getNickName()));
			user.setPic(appConnect.getImageUrl());
			userMapper.insert(user);
		} else {
			user = userMapper.selectById(userId);
		}
		appConnect.setUserId(user.getId());
		appConnectMapper.insert(appConnect);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.isBlank(username)){
			throw new UsernameNotFoundException("无法获取用户信息");
		}
		return loadUserByAppIdAndBizUserId(username);
	}
}
