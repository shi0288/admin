package com.xyauto.qa.service;

import java.util.List;

import com.xyauto.qa.entity.Brand;

/**
 * Created by shiqm on 2017/3/27.
 */
public interface BrandService extends ABaseService<Brand,Integer>{
	/**
	 * 按照名称拼音排序
	 * @return
	 */
	List<Brand> selectBrandOrderByName();
}
