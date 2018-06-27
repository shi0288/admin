package com.xyauto.system.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.controller.ABaseController;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.Menu;
import com.xyauto.system.entiy.Operate;
import com.xyauto.system.service.IMenuService;
import com.xyauto.system.service.IOperateService;
import com.xyauto.system.vo.MenuOperateVo;

@Controller
@RequestMapping("user/system")
public class MenuController extends ABaseController {
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IOperateService operateService;

	@RequestMapping("menuList")
	public void selectMenu(Menu menu, ModelMap modelMap) {
		List<MenuOperateVo> menuOperateList=menuService.selectMenuOperate(menu);
		modelMap.put("menuOperateList", menuOperateList);
		//查询父级菜单
		Menu menuFather = new Menu();
		menu.setParentId(0);
		List<Menu> menuFatherList = menuService.selectList(menuFather);
		modelMap.put("menuFatherList", menuFatherList);
	}

	@RequestMapping("toAddMenu")
	public void toAddMenu(ModelMap modelMap) {
		Menu menu = new Menu();
		menu.setParentId(0);
		List<Menu> menuList = menuService.selectList(menu);
		modelMap.put("menuList", menuList);
	}
	
	@RequestMapping("selectOperte")
	@ResponseBody
	public QaResult selectOperte(Integer mid){
		Operate operate=new Operate();
		operate.setMid(mid);
		List<Operate> operateList = operateService.selectList(operate);
		return new QaResult(operateList);
	}

	@RequestMapping("createMenu")
	@ResponseBody
	public QaResult createMenu(Menu menu) {
		if (StringUtils.isBlank(menu.getMenuName())) {
			return new QaResult(ErrorCode.OVER, "菜单名称不能为NULL");
		}
		menuService.insert(menu);
		return new QaResult(ErrorCode.OK, "保存成功");
	}
	
	@RequestMapping("toEditMenu")
	public void editMenu(Integer mid,ModelMap modelMap){
		Menu menu=menuService.selectMenu(mid);
		modelMap.put("menu", menu);
		//菜单列表
		Menu menu1 = new Menu();
		menu1.setParentId(0);
		List<Menu> menuList = menuService.selectList(menu1);
		modelMap.put("menuList", menuList);
		//查询菜单下所属功能列表
		Operate operate=new Operate();
		if (menu.getParentId()>0) {
			operate.setMid(menu.getParentId());
		}else{
			operate.setMid(mid);
		}		
		List<Operate> operateList = operateService.selectList(operate);
		modelMap.put("operateList", operateList);
	}
	
	@RequestMapping("saveMenu")
	@ResponseBody
	public QaResult saveMenu(Menu menu){
		menuService.updateMenu(menu);
		return new QaResult();
	}

	@RequestMapping("operateList")
	public void selectOperate(ModelMap modelMap, Operate operate) {
		List<MenuOperateVo> operateList = operateService.selectMenuOperate(operate);
		modelMap.put("operateList", operateList);
		// 查询菜单信息
		Menu menu = new Menu();
		menu.setParentId(0);
		List<Menu> menuList = menuService.selectList(menu);
		modelMap.put("menuList", menuList);
		modelMap.put("operate", operate);
	}

	@RequestMapping("toAddOperate")
	public void toAddOperate(ModelMap modelMap) {
		// 查询菜单信息
		Menu menu = new Menu();
		menu.setParentId(0);
		List<Menu> menuList = menuService.selectList(menu);
		modelMap.put("menuList", menuList);
	}

	@RequestMapping("createOperate")
	@ResponseBody
	public QaResult createOperate(ModelMap modelMap,Operate operate) {
		if (StringUtils.isBlank(operate.getOperateName())) {
			return new QaResult(ErrorCode.OVER, "功能名称不能为NULL");
		}
		if (StringUtils.isBlank(operate.getUrl())) {
			return new QaResult(ErrorCode.OVER, "功能地址不能为NULL");
		}
		if (operate.getMid()==null||operate.getOperateType()==null) {
			return new QaResult(ErrorCode.OVER, "参数不合法");
		}
		operateService.insert(operate);
		return new QaResult(ErrorCode.OK, "保存成功");		
	}
	@RequestMapping("toEditOperate")
	public void toEditOperate(ModelMap modelMap,Integer operateId){
		Operate operate=operateService.selectOperate(operateId);
		modelMap.put("operate", operate);
		//菜单列表
		Menu menu1 = new Menu();
		menu1.setParentId(0);
		List<Menu> menuList = menuService.selectList(menu1);
		modelMap.put("menuList", menuList);
	}
	
	@RequestMapping("saveOperate")
	@ResponseBody
	public QaResult saveOperate(Operate operate){
		operateService.updateOperate(operate);
		return new QaResult();
	}

	@ModelAttribute("urlObj")
	public JSONObject getUrlObj() {
		return super.getUrlObj();
	}
}
