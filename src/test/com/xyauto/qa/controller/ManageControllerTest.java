package com.xyauto.qa.controller;

import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.entity.Brand;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.mapper.BlocksMapper;
import com.xyauto.qa.mapper.CategoryMapper;
import com.xyauto.qa.mapper.QuestionMapper;
import com.xyauto.qa.mapper.UserMapper;
import com.xyauto.qa.service.BlocksService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.TemplateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by shiqm on 2017/3/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManageControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private TemplateUtils templateUtils;

    @Autowired
    private RUserService rUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlocksMapper blocksMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BlocksService blocksService;

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void testExpertList() throws Exception {


//        System.out.println(userService.getUserAndInfo(1L).getBrands().size());
//        List list =userService.getUserAndInfo(1L).getBrands();
//        for(int i=0;i<list.size();i++){
//
//            Brand brand= (Brand) list.get(i);
//
//            if(brand.getSeries().size()==1){
//                Series series=brand.getSeries().get(0);
//                System.out.println(series.getBrand());
//                System.out.println(series.getSeriesId());
//            }
//
//        }


        System.out.println(questionMapper.getQuestionDesc(8018L).getContent());
//        System.out.println(blocksService.getBlockByName(CommonCons.Blocks_Flag.USER_EXPERT_RECOMMEND));
//        System.out.println(userMapper.getById("1").getCategories().size());
//        System.out.println(categoryMapper.getCategoryByIds("1,2").size());
    }
}