package work.ambitlu.core.cache;

import org.springframework.stereotype.Component;
import work.ambitlu.core.entity.GlobalProperties;

/**
 * 全局配置缓存
 *
 * @Date: 2020/12/14 18:27
 * @Author: Ambi 赵帅
 * @Since: JDK 1.8.251
 */
@Component
public class GlobalPropertiesCache extends AbstractCacheService<GlobalProperties> {

	private static final String CACHEKEY = "GlobalPropertiesCache";

	public boolean set(GlobalProperties globalProperties){
		return super.set(CACHEKEY, globalProperties);
	}

	public GlobalProperties get() {
		return super.get(CACHEKEY,GlobalProperties.class);
	}
}
