package com.xyauto.system.service;


import java.util.List;

import com.xyauto.system.entiy.Menu;
import com.xyauto.system.vo.MenuOperateVo;

public interface IMenuService {
	int insert(Menu menu);
	List<Menu> selectList(Menu menu);
	Menu selectMenu(Integer mid);
	int updateMenu(Menu menu);
	/**
	 * 查询所有菜单，包含关联的功能
	 * @param menu
	 * @return
	 */
	List<MenuOperateVo> selectMenuOperate(Menu menu);
}
