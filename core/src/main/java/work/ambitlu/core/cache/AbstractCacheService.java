package work.ambitlu.core.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 *
 * @author Ambi 赵帅
 * @date 2021/2/3 22:48
 */
@Service
@Slf4j
public abstract class AbstractCacheService<T> {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public boolean set(String key ,T value){
		try {
			//任意类型转换成String
			String val = beanToString(value);
			if(val==null||val.length()<=0){
				return false;
			}
			stringRedisTemplate.opsForValue().set(key,val);
			return true;
		}catch (Exception e){
			log.info(e.getMessage());
			return false;
		}
	}

	public boolean add(String key,T value,int time){
		try {
			//任意类型转换成String
			String val = beanToString(value);
			if(time>0){
				stringRedisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
			}else{
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public T get(String key,Class<T> clazz){
		try {
			String value = stringRedisTemplate.opsForValue().get(key);
			return stringToBean(value,clazz);
		}catch (Exception e){
			log.info(e.getMessage());
			return null ;
		}
	}
	public boolean delete(String key){
		try{
			return BooleanUtils.isTrue(stringRedisTemplate.delete(key));
		}catch (Exception e){
			log.info(e.getMessage());
			return false ;
		}
	}
	@SuppressWarnings("unchecked")
	private T stringToBean(String value, Class<T> clazz) {
		if(value==null||value.length()<=0||clazz==null){
			return null;
		}
		if(clazz ==int.class ||clazz==Integer.class){
			return (T)Integer.valueOf(value);
		}
		else if(clazz==long.class||clazz==Long.class){
			return (T)Long.valueOf(value);
		}
		else if(clazz==String.class){
			return (T)value;
		}else {
			return JSON.toJavaObject(JSON.parseObject(value),clazz);
		}
	}


	private String beanToString(T value) {

		if(value==null){
			return null;
		}
		Class <?> clazz = value.getClass();
		if(clazz == Integer.class){
			return ""+value;
		}
		else if(clazz == Long.class){
			return ""+value;
		}
		else if(clazz==String.class){
			return (String)value;
		}else {
			return JSON.toJSONString(value);
		}
	}
}
