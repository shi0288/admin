package com.xyauto.qa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.SpiderQuestion;
import com.xyauto.qa.entity.vo.SpiderQuestionVo;

public interface ISpiderQuestionService{
	PageInfo<SpiderQuestionVo> selectByPage(SpiderQuestion spiderQuestion);
	
	int delSpider(Long id);

}
