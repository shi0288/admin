package com.xyauto.utils.enumutil;

import java.util.HashMap;
import java.util.Map;

public enum FrozenReasonEnum {
	reason1("政治敏感", 1), 
	reason2("人身攻击", 2), 
	reason3("淫秽色情", 3), 
	reason4("广告", 4), 
	reason5("其他", 5),
	reason6("水贴", 6);

	private Integer code;
	private String value;

	private FrozenReasonEnum(String value, Integer code) {
		this.code=code;
		this.value=value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public  static Map<Integer, String> reasonMap=FrozenReasonEnum.getMap();
	public  static Map<Integer, String> getMap(){
		Map<Integer, String> map=new HashMap<Integer, String>();
		for (FrozenReasonEnum itme : FrozenReasonEnum.values()) {
			map.put(itme.getCode(), itme.getValue());
		}
		return map;		
	}
}
