package work.ambitlu.utils;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 缓存
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:30
 */
@Component
@AllArgsConstructor
public class CacheManagerUtil {

	private CacheManager cacheManager;

	@SuppressWarnings({"unchecked"})
	public <T> T getCache(String cacheName,String key) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			return null;
		}
		Cache.ValueWrapper valueWrapper = cache.get(key);
		if (valueWrapper == null) {
			return null;
		}
		return (T)valueWrapper.get();
	}

	public void putCache(String cacheName,String key, Object value) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			cache.put(key, value);
		}
	}

	public void evictCache(String cacheName,String key) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			cache.evict(key);
		}
	}
}
