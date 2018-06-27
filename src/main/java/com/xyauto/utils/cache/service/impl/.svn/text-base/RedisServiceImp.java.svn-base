package com.xyauto.utils.cache.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.xyauto.utils.cache.service.ICacheService;

@Service
public class RedisServiceImp implements ICacheService {
	@Autowired
	private StringRedisTemplate  stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
	    RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    this.redisTemplate = redisTemplate;
	}
	@Override
	public Integer getInteger(String key) {
		try {
			String value=stringRedisTemplate.opsForValue().get(key);
			if (StringUtils.isBlank(value)) {
				return null;
			}else{
				return Integer.parseInt(value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getStr(String key) {
		try {
			String str = (String) stringRedisTemplate.opsForValue().get(key);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean set(String key, String value, long timeout, TimeUnit unit) {
		try {
			stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delString(String key) {
		try {
			stringRedisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return false;
	}

	@Override
	public boolean set(String key, String value, long timeout) {
		return this.set(key, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public boolean setIfAbsent(String key, String value,long timeout) {
		stringRedisTemplate.opsForValue().setIfAbsent(key, value);
		Long time=getTime(key, TimeUnit.SECONDS);
		if (time!=null&&time>0) {
			return false;
		}
		if (timeout<=0) {
			timeout=600L;
		}
		return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);		
	}
	@Override
	public boolean set(String key,Map<String, Object> map){
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public  Map<String, Object> getMap(String key) {
		try {
			HashOperations<String, String, Object> hashOperations= 
					redisTemplate.opsForHash();
			return hashOperations.entries(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean del(String key) {
		try {
			redisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return false;
	}

	@Override
	public boolean set(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public String get(String key) {
		try {
			String value=(String)redisTemplate.opsForValue().get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	@Override
	public Object getObject(String key) {
		Object value=null;
		try {
			 value=redisTemplate.opsForValue().get(key);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public boolean set(String key, Object value, long timeout, TimeUnit unit) {
		try {
			redisTemplate.opsForValue().set(key, value, timeout, unit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Long getTime(String key,TimeUnit unit) {
		// TODO Auto-generated method stub
		return stringRedisTemplate.getExpire(key, unit);
	}
	
}
