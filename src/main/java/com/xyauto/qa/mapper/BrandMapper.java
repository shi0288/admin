package com.xyauto.qa.mapper;

import com.xyauto.qa.entity.Brand;
import com.xyauto.qa.util.BaseMapper;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {
    List<Brand>  findBrand(String name);
    List<Brand> selectBrandOrderByName();
}