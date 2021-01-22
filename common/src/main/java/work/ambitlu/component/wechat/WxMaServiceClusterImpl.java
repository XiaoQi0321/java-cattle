package work.ambitlu.component.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:11
 */
@Slf4j
public class WxMaServiceClusterImpl extends WxMaServiceImpl {

	private static final String REDISSON_LOCK_PREFIX = "redisson_lock:";

	private RedissonClient redissonClient;

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@Override
	public String getAccessToken(boolean forceRefresh) throws WxErrorException {
		if (!this.getWxMaConfig().isAccessTokenExpired() && !forceRefresh) {
			return this.getWxMaConfig().getAccessToken();
		}

		RLock rLock = redissonClient.getLock(REDISSON_LOCK_PREFIX + ":WxMaServiceCluster:getAccessToken");

		try {
			boolean lockSuccess;
			try {
				lockSuccess = rLock.tryLock(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				return this.getWxMaConfig().getAccessToken();
			}

			if (!lockSuccess) {
				//TODO 需要定义自己的异常
				throw new RuntimeException("服务器繁忙，请稍后再试");
			}

			if (!this.getWxMaConfig().isAccessTokenExpired()) {
				return this.getWxMaConfig().getAccessToken();
			}

			String url = String.format(WxMaService.GET_ACCESS_TOKEN_URL, this.getWxMaConfig().getAppid(),
					this.getWxMaConfig().getSecret());
			String resultContent = HttpUtil.get(url);
			WxError error = WxError.fromJson(resultContent);
			if (error.getErrorCode() != 0) {
				throw new WxErrorException(error);
			}
			WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
			this.getWxMaConfig().updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());

			return this.getWxMaConfig().getAccessToken();

		} finally {
			rLock.unlock();
		}

	}
}
