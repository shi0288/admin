package com.xyauto.system.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.system.entiy.UserPower;
import com.xyauto.system.vo.UserPowerVo;

public interface UserPowerMapper {
	/**
	 * 根据用户id查询所分配的菜单及未分配的菜单
	 * @param uid
	 * @return
	 */
	List<UserPowerVo> selectMenuPowerByUid(Long uid);
	/**
	 * 删除权益
	 * @param userPower：uid必传
	 * @return
	 */
	int deleteUserPower(UserPower userPower);
	/**
	 * 保存用户权益
	 * @param map
	 * @return
	 */
	int saveUserPower(Map<String, Object> map);
	/**
	 * 查询用户所分配的菜单权限的父菜单
	 * 
	 */
	List<UserPowerVo> selectParmentMenu(Long uid);
	/**
	 * 查询所有分配的权限
	 * @param uid
	 * @param type：1菜单，2操作
	 * @return
	 */
	List<UserPowerVo> selectPower(UserPower power);
}
