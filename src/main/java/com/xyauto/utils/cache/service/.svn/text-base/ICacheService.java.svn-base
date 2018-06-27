package com.xyauto.utils.cache.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public interface ICacheService {
	@Deprecated
	public Integer getInteger(String key);
	@Deprecated
	public String getStr(String key);
	@Deprecated
	public  boolean set(String key,String value,long timeout);
	@Deprecated
	public  boolean set(String key,String value,long timeout, TimeUnit unit);
	@Deprecated
	public boolean delString(String key);
	/**
	 * 默认10分钟，时间单位秒
	 * @param key
	 * @param value
	 * @param timeout:缓存失效时间
	 * @return
	 */
	@Deprecated
	public boolean setIfAbsent(String key,String value,long timeout);
	public boolean set(String key,Map<String, Object> map);
	public boolean del(String key);
	public Map<String, Object> getMap(String key);
	public boolean set(String key,String value);
	public String get(String key);
	public boolean set(String key,Object value);
	public boolean set(String key,Object value,long timeout, TimeUnit unit);
	public Object getObject(String key);
	/**
	 * 获取key的过期时间
	 * @param key
	 * @return
	 */
	public Long getTime(String key,TimeUnit unit);
}
