package work.ambitlu.component.wechat;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 基于Redis的微信配置provider
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:06
 */
@Component
public class WxMaDefaultConfig extends WxMaDefaultConfigImpl {

	private static final String ACCESS_TOKEN_KEY = "wxMa:access_token:";

	private static final String JSAPI_TICKET_KEY = "wxMa:jsapi_ticket:";

	private static final String CARD_API_TICKET_KEY = "wxMa:card_api_ticket:";

	private static final String WX_MA_ACCESS_TOKEN_LOCK = "wxMa:access_token_lock:";

	private static final String WX_MA_JSAPI_TICKET_LOCK = "wxMa:jsapi_ticket_lock:";

	private static final String WX_MA_CARD_API_TICKET_LOCK = "wxMa:card_api_ticket_lock:";

	@Autowired
	private RedissonClient redissonClient;


}
