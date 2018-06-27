package com.xyauto.qa.core.conver;

import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.entity.Category;
import com.xyauto.qa.service.CategoryService;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shiqm on 2017-05-03.
 */
public class CategoryConver implements TemplateMethodModelEx {
	
	@Autowired
	private CategoryService categoryService;
	
    @Override
    public List exec(List arguments) throws TemplateModelException {
        if (null != arguments && 2 == arguments.size()) {
            String category_id = String.valueOf(arguments.get(0));
            String temp = String.valueOf(arguments.get(1));                      
            try {
            	if (category_id==null) {
					return null;
				}
            	Category category=new Category();
        		category.setParent(Integer.parseInt(category_id));
        		List<Category> categories=categoryService.get(category);
        		return categories;
            }catch (Exception e){
                return null;
            }
        } else {
            return null;
        }
    }
}
