package com.xyauto.qa.service.impl;

import java.util.List;

import com.xyauto.qa.entity.Brand;
import com.xyauto.qa.mapper.BrandMapper;
import com.xyauto.qa.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/27.
 */
@Service
public class BrandServiceImpl extends ABaseServiceImpl<Brand, Integer> implements BrandService {

	@Autowired
	private BrandMapper brandMapper;
	@Override
	public List<Brand> selectBrandOrderByName() {
		// TODO Auto-generated method stub
		return brandMapper.selectBrandOrderByName();
	}
}
