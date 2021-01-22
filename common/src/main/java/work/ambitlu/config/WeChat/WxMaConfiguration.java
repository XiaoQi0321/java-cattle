package work.ambitlu.config.WeChat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.ambitlu.component.wechat.WxMaDefaultConfig;
import work.ambitlu.component.wechat.WxMaServiceClusterImpl;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:05
 */
@Configuration
@AllArgsConstructor
@ConditionalOnClass(WxMaService.class)
public class WxMaConfiguration {


	private final WxMaDefaultConfig wxMaDefaultConfig;

	private final RedissonClient redissonClient;

	@Bean
	public WxMaService wxMaService() {
		WxMaServiceClusterImpl service = new WxMaServiceClusterImpl();
		service.setWxMaConfig(wxMaDefaultConfig);
		service.setRedissonClient(redissonClient);
		return service;
	}


}