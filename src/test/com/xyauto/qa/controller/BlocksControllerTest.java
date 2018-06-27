package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.autoconfigure.MapperProperties;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.mapper.QuestionTypeBiMapper;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.entity.*;
import com.xyauto.qa.mapper.AdminLogsMapper;
import com.xyauto.qa.mapper.QuestionMapper;
import com.xyauto.qa.mapper.UserCarMapper;
import com.xyauto.qa.mapper.UserMapper;
import com.xyauto.qa.service.AdminLogsService;
import com.xyauto.qa.service.BlocksService;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QuestionTypeBiCompare;
import com.xyauto.qa.webservice.EmployeeService;
import com.xyauto.qa.webservice.EmployeeServiceSoap;
import com.xyauto.qa.webservice.LoginResult;
import com.xyauto.system.service.IUserPowerService;
import com.xyauto.system.vo.UserPowerVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.*;

/**
 * Created by shiqm on 2017/3/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlocksControllerTest {


    @Autowired
    private BlocksService blocksService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCarMapper userCarMapper;

    @Autowired
    private QaUserService qaUserService;

    @Autowired
    private RUserService rUserService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private IQuestionBIService questionBIService;

    @Autowired
    private AdminLogsMapper adminLogsMapper;

    @Autowired
    private AdminLogsService adminLogsService;


    @Autowired
    private QuestionTypeBiMapper questionTypeBiMapper;


    @Autowired
    private MapperProperties mapperProperties;


    @Autowired
    private IUserPowerService userPowerService;


    @Test
    public void testTest1() throws Exception {

//
//        System.out.println(blocksService.getPcBannerList());
//        System.out.println(blocksService.getMobileBannerList());
//        System.out.println(blocksService.getPcNoticeList());


        //System.out.println(userCarMapper.getAllByUid(1L).get(0).getSeriesId());
//        System.out.println(userCarMapper.deleteById(1L));
//        UserCar userCar=new UserCar();
//        userCar.setBrandId(1);
//        userCar.setSeriesId(1);
//        userCar.setUid(1L);
////        System.out.println(userCarMapper.insert(userCar));
//
//        System.out.println(userCarMapper.saveUserCar(userCar));
//
//        System.out.println(rUserService.getUserIdByNickName("嗯嗯好的自助餐"));
//        System.out.println(rUserService.getUserIdByNickName("www"));

//        Question question = new Question();
//
//
////        PageHelper.startPage(1, 20);
////        List list = questionMapper.getQuestionList(question);
////        Question question1 = (Question) list.get(0);
////        System.out.println(question1.getContent());
////        System.out.println(question1.getCity().getName());
////        System.out.println(question1.getCategory().getName());
////        System.out.println(question1.getSeries().size());
//
////        System.out.println(qaUserService.getUserDesc("33"));
//        PageInfo pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> questionMapper.getQuestionList(question));
//

//
//        User user = userMapper.getUserBrandsById(1L);
//
//        System.out.println( user.getBrands().size());
//        Brand brand=user.getBrands().get(0);
//        System.out.println(brand.getSeries().size());

//        System.out.println(user.getExpert_images());
//        System.out.println(user.getExpert_name());
//        System.out.println(user.getCars().size());
//        System.out.println();


//        System.out.println(rUserService.getUserList("35"));

//        System.out.println(qaUserService.createQuestionNoPic("3","122222222222","2",null,null,"302","aaaaa",0));


//        System.out.println(qaUserService.createQuestionNoPic("0","@t@1233@t@","1",null,null,null,null,null));


//        System.out.println(qaUserService.createAnswerNoPic(21176L,"sdfasfsdfsa","","",null,null,2L));


//        List<QuestionTypeBi> typeList = questionBIService
//                .selectQuestionTypeBi(null);
//        List typeArr = new ArrayList();
//        Map<String, Integer> mapQuestion = new LinkedHashMap<>();
//        Map<String, Integer> mapAnswer = new LinkedHashMap<>();
//        JSONObject res = new JSONObject();
//        res.put("types", typeArr);
//        res.put("quesInfo", mapQuestion);
//        res.put("answerInfo", mapAnswer);
//        for (int i = 0; i < typeList.size(); i++) {
//            QuestionTypeBi temp = typeList.get(i);
//            if ("source".equals(temp.getType())) {
//                String type = ConvertCons.ResourceCover.get(temp.getTypeValue());
//                System.out.println(type);
//                typeArr.add(type);
//                mapQuestion.put(type, temp.getQuestionCount());
//                mapAnswer.put(type, temp.getAnswerCount());
//            }
//        }
//        System.out.println(typeArr);
//        System.out.println(mapQuestion.keySet());
//        System.out.println(mapAnswer.keySet());
//
//        AdminLogs adminLogs = new AdminLogs();
//        adminLogs.setUsername("shiqm");
//        adminLogs.setAction(1);
//        adminLogs.setModule(1);
//        adminLogs.setContent("aaa");
//
//        System.out.println(adminLogsMapper.insert(adminLogs));

//        AdminLogs adminLogs=new AdminLogs();
//        adminLogs.setPageSort("created_at desc");
//        adminLogs.setPageNum(55);
//        PageInfo<AdminLogs> pageInfo=adminLogsService.findByPager(adminLogs);
//        System.out.println(pageInfo.getList().get(0).getCreated_at());

//        PageHelper.startPage(1, CommonCons.Pager_Flag.PAGE_LIMIT);
//        OrderByHelper.orderBy("created_at desc");
//        List list =adminLogsMapper.select(adminLogs);
//        adminLogs= (AdminLogs) list.get(0);
//        System.out.println(adminLogs.getId());


//
//        List<QuestionTypeBi> typeList = questionBIService
//                .selectQuestionTypeBi(new HashMap(){
//                    {
//                    put("start","2017-05-25");
//                    }
//                });
//
//        System.out.println(typeList.size());


//        Date time = new Date();
//        int daySplit = 5;
//        String dateStartTime = DateUtils.dateToStr(DateUtils.beforDate(time, daySplit));
//        String dateEndTime = DateUtils.dateToStr(DateUtils.beforDate(time, 0));
//        System.out.println(dateStartTime);
//        System.out.println(dateEndTime);
//        List<QuestionTypeBi> typeList = questionTypeBiMapper
//                .selectCountByDays(new HashMap() {
//                    {
//                        put("startTime", dateStartTime);
//                        put("endTime", dateEndTime);
//                    }
//                });
//
//        Collections.sort(typeList, new QuestionTypeBiCompare());
//        System.out.println(typeList.toString());



//        JSONObject data = new JSONObject();
//        JSONObject object = new JSONObject();
//        for (QuestionTypeBi bi : typeList) {
//            String type = ConvertCons.ResourceCover.get(bi.getTypeValue());
//            if (object.containsKey(type)) {
//                JSONArray jsonArray = object.getJSONArray(type);
//                jsonArray.add(bi.getQuestionCount());
//            } else {
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.add(bi.getQuestionCount());
//                object.put(type, jsonArray);
//            }
//        }
//        JSONArray array=new JSONArray();
//        for (int i = daySplit; i >0 ; i--) {
//            array.add(DateUtils.dateToStr(DateUtils.beforDate(time, i),"yy年M月d日"));
//        }
//        data.put("values", object);
//        data.put("times", array);
//        System.out.println(data.toJSONString());

//        System.out.println(mapperProperties.getIdentity());
//        System.out.println(mapperProperties.getIdentity());
//        System.out.println(mapperProperties.getMappers());

        List<UserPowerVo> menuParents = userPowerService.selectParmentMenu(13L);
        //查询所有子集菜单
        List<UserPowerVo> menuChildrens = userPowerService.selectPower(13L);


        for (UserPowerVo u : menuParents
                ) {
            System.out.println(u);

        }
        for (UserPowerVo u : menuChildrens
                ) {
            System.out.println(u);

        }
        //查询所有子集菜单
//        List<UserPowerVo> menuList = userPowerService.selectPower(user.getUid());

    }
}