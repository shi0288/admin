package com.xyauto.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.system.entiy.UserPower;
import com.xyauto.system.mapper.UserPowerMapper;
import com.xyauto.system.service.IUserPowerService;
import com.xyauto.system.vo.UserPowerVo;

@Service
public class UserPowerServiceImpl implements IUserPowerService{

	@Autowired
	private UserPowerMapper userPowerMapper;
	@Override
	public List<UserPowerVo> selectMenuPowerByUid(Long uid) {	
		List<UserPowerVo> list=userPowerMapper.selectMenuPowerByUid(uid);
		return list;
	}

	@Override
	public List<UserPowerVo> selectPower(Long uid) {
		UserPower power =new UserPower();
		power.setUid(uid);
		power.setType((short) 1);
		return userPowerMapper.selectPower(power);
	}

	@Override
	public int saveUserPower(Long uid,String[] powerId,Short type) {
		if (type==null) {
			type=(short)1;
		}
		//直接删除原有权益
		UserPower userPower=new UserPower();
		userPower.setUid(uid);
		userPower.setType(type);
		userPowerMapper.deleteUserPower(userPower);
		//保存权益
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uid",uid );
		map.put("type",type );
		map.put("powerId", powerId);
		
		userPowerMapper.saveUserPower(map);
		return 0;
	}

	@Override
	public List<UserPowerVo> selectParmentMenu(Long uid) {
		
		return userPowerMapper.selectParmentMenu(uid) ;
	}

}
