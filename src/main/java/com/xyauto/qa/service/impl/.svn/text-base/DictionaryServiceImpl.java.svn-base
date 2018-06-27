package com.xyauto.qa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Dictionary;
import com.xyauto.qa.mapper.DictionaryMapper;
import com.xyauto.qa.service.IDictionaryService;

@Service
public class DictionaryServiceImpl implements IDictionaryService{

	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Override
	public PageInfo<Dictionary>  select(Dictionary dictionary) {
		 PageInfo<Dictionary> pageInfo = PageHelper.startPage(
				 dictionary.getPageNum(), dictionary.getPageSize())
	                .doSelectPageInfo(
	                        () -> dictionaryMapper.select(dictionary));
		return pageInfo;
	}

	@Override
	public int insert(Dictionary dictionary) {
		// TODO Auto-generated method stub
		dictionaryMapper.insert(dictionary);
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		dictionaryMapper.delete(id);
		return 0;
	}

}
