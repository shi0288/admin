package com.xyauto.qa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Array;
import com.xyauto.qa.entity.Category;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.SpiderQuestion;
import com.xyauto.qa.entity.vo.SpiderQuestionVo;
import com.xyauto.qa.mapper.SpiderQuestionMapper;
import com.xyauto.qa.service.CategoryService;
import com.xyauto.qa.service.ISpiderQuestionService;
import com.xyauto.qa.service.SeriesService;

@Service
public class SpiderQuestionServiceImpl  implements
		ISpiderQuestionService {
	
	@Autowired
	private SpiderQuestionMapper spiderQuestionMapper;
	@Autowired
	private SeriesService seriesService ;
	@Autowired
	private CategoryService categoryService;
	@Override
	public PageInfo<SpiderQuestionVo> selectByPage(SpiderQuestion spiderQuestion) {
		PageInfo<SpiderQuestion> pageInfo = PageHelper.startPage(spiderQuestion.getPageNum(),
				10).doSelectPageInfo(
				() -> spiderQuestionMapper.select(spiderQuestion));
		List<SpiderQuestionVo> list=new ArrayList<SpiderQuestionVo>();
		for (SpiderQuestion spiderQuestion1 : pageInfo.getList()) {
			SpiderQuestionVo vo=new SpiderQuestionVo();
			JSONObject obj=JSONObject.parseObject(spiderQuestion1.getContent());
			String brand_name=obj.getString("brand");
			String series_name=obj.getString("car_Type");
			String category_name=obj.getString("type1");
			String sub_category_name=obj.getString("type2");
			String content=obj.getString("answer");
			String url=obj.getString("url");
			//查询车系，查询分类，查询二级分类
			Series series=new Series();
			series.setName(series_name);
			series=seriesService.getOne(series);
			if (series!=null&&series.getSeriesId()!=null) {
				vo.setSeries_id(series.getSeriesId());
				vo.setSeries_name(series.getName());
			}
			Category category=new Category();
			category.setName(category_name);
			category.setParent(0);
			category=categoryService.getOne(category);
			if (category!=null&&category.getCategoryId()!=null) {
				vo.setCategory_id(category.getCategoryId());
				vo.setCategory_name(category.getName());
				category=new Category();
				category.setParent(vo.getCategory_id());
				category.setName(sub_category_name);
				category=categoryService.getOne(category);
				if (category!=null&&category.getCategoryId()!=null) {
					vo.setSub_category_id(category.getCategoryId());
					vo.setSub_category_name(category.getName());
				}
			}			
			vo.setId(spiderQuestion1.getId());
			vo.setContent(content);
			vo.setUrl(url);
			list.add(vo);
		}
		PageInfo<SpiderQuestionVo> page=new PageInfo<SpiderQuestionVo>();
		page.setList(list);
		page.setTotal(pageInfo.getTotal());
		page.setPages(pageInfo.getPages());
		page.setPageNum(pageInfo.getPageNum());
		page.setPageSize(pageInfo.getPageSize());
		return page;
	}
	@Override
	public int delSpider(Long id) {
		SpiderQuestion spiderQuestion=new SpiderQuestion();
		spiderQuestion.setId(id);
		spiderQuestion.setStatus((short)-1);
		return spiderQuestionMapper.updateByPrimaryKeySelective(spiderQuestion);
	}

}
