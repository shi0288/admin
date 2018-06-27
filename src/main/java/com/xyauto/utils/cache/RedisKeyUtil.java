package com.xyauto.utils.cache;


public class RedisKeyUtil {
	public static String base = "ask_";

	public enum Model {
		busses, question, answer, manger, bidata,user
	}

	public static String getKey(Model model, String key) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(base).append(model.toString()).append("_").append(key);
		return buffer.toString();
	}
}
