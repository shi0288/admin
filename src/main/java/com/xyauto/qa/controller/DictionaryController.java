package com.xyauto.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Dictionary;
import com.xyauto.qa.service.IDictionaryService;
import com.xyauto.qa.util.QaResult;

@Controller
@RequestMapping("user/question")
public class DictionaryController extends ABaseController{
	@Autowired
	private IDictionaryService dictionaryService;
	@RequestMapping("dictionaryList")
	public void selectDictionaryList(Dictionary dictionary,ModelMap modelMap){		
		PageInfo<Dictionary> pageInfo =dictionaryService.select(dictionary);
		modelMap.put("pager", pageInfo);
	}
	
	@RequestMapping("deleteDictionary")
	@ResponseBody
	public QaResult deleteDictionary(Long id){
		dictionaryService.delete(id);
		return new QaResult();
	}
	
	@RequestMapping("toAddDictionary")
	public void toAddDictionary(){
		System.out.println("1231");
	}
	@RequestMapping("createDictionary")
	@ResponseBody
	public QaResult createDictionary(Dictionary dictionary){
		dictionaryService.insert(dictionary);
		return new QaResult();
	}
	
	@ModelAttribute("urlObj")
    public JSONObject getUrlObj() {
        return super.getUrlObj();
    }
}
