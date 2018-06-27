package com.xyauto.system.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.system.entiy.Menu;
import com.xyauto.system.mapper.MenuMapper;
import com.xyauto.system.service.IMenuService;
import com.xyauto.system.vo.MenuOperateVo;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMappper;
	@Override
	public int insert(Menu menu) {		
		if (menu.getOperateId()==null) {
			menu.setOperateId(0);
		}
		menuMappper.insert(menu);
		return 0;
	}
	@Override
	public List<Menu> selectList(Menu menu) {
		// TODO Auto-generated method stub
		return menuMappper.selectList( menu);
	}
	@Override
	public Menu selectMenu(Integer mid) {		
		return menuMappper.selectMenu(mid);
	}
	@Override
	public int updateMenu(Menu menu) {
		menuMappper.updateMenu(menu);
		return 0;
	}
	@Override
	public List<MenuOperateVo> selectMenuOperate(Menu menu) {
		
		return menuMappper.selectMenuOperate(menu);
	}
	
}
