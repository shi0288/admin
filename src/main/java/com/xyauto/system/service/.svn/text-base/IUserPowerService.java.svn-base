package com.xyauto.system.service;

import java.util.List;

import com.xyauto.system.vo.UserPowerVo;

public interface IUserPowerService {
	/**
	 * 根据用户id查询所有菜单---已分配的带标识
	 * @param uid
	 * @return
	 */
	List<UserPowerVo> selectMenuPowerByUid(Long uid);
	
	/**
	 * 查询分配的所有权益
	 * @param uid
	 * @param type :1菜单权限，2操作权限
	 * @return
	 */
	List<UserPowerVo> selectPower(Long uid);
	
	/**
	 * 删除原有权益，并保存新的，无论是否有重合
	 * @param uid
	 * @param powerIds
	 * @param type 默认保存菜单权益(1)
	 * @return
	 */
	int saveUserPower(Long uid,String[] powerIds,Short type);
	
	/**
	 * 查询用户所分配的菜单权限的父菜单
	 * 
	 */
	List<UserPowerVo> selectParmentMenu(Long uid);
}
