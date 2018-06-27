package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.mapper.QuestionTypeBiMapper;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;
import com.xyauto.qa.util.QuestionTypeBiCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by shiqm on 2017-05-16.
 */

@Controller
@RequestMapping("user/chartsData")
public class ChartsController extends AjaxBaseController {

    @Autowired
    private IQuestionBIService questionBIService;
    @Autowired
    private QuestionTypeBiMapper questionTypeBiMapper;

    @RequestMapping(value = "questionAndAnswer", method = RequestMethod.POST)
    @ResponseBody
    public QaResult getQuestionAndAnswer() {
        List<QuestionTypeBi> typeList = questionBIService
                .selectQuestionTypeBi(null);
        List typeArr = new ArrayList();
        Map<String, Integer> mapQuestion = new LinkedHashMap<>();
        Map<String, Integer> mapAnswer = new LinkedHashMap<>();
        JSONObject res = new JSONObject();
        res.put("types", typeArr);
        res.put("quesInfo", mapQuestion);
        res.put("answerInfo", mapAnswer);
        for (int i = 0; i < typeList.size(); i++) {
            QuestionTypeBi temp = typeList.get(i);
            if ("source".equals(temp.getType())) {
                String type = ConvertCons.ResourceCover.get(temp.getTypeValue());
                typeArr.add(type);
                mapQuestion.put(type, temp.getQuestionCount());
                mapAnswer.put(type, temp.getAnswerCount());
            }
        }
        return new QaResult(res);
    }


    @RequestMapping(value = "questionBi", method = RequestMethod.POST)
    @ResponseBody
    public QaResult questionBi() {
        Date time = new Date();
        int daySplit = 7;
        String dateStartTime = DateUtils.dateToStr(DateUtils.beforDate(time, daySplit));
        String dateEndTime = DateUtils.dateToStr(DateUtils.beforDate(time, 1));
        List<QuestionTypeBi> typeList = questionTypeBiMapper
                .selectByDays(new HashMap() {
                    {
                        put("startTime", dateStartTime);
                        put("endTime", dateEndTime);
                    }
                });
        Collections.sort(typeList, new QuestionTypeBiCompare());
        JSONObject data = new JSONObject();
        JSONObject object = new JSONObject();
        for (QuestionTypeBi bi : typeList) {
            String type = ConvertCons.ResourceCover.get(bi.getTypeValue());
            if (object.containsKey(type)) {
                JSONArray jsonArray = object.getJSONArray(type);
                jsonArray.add(bi.getQuestionCount());
            } else {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(bi.getQuestionCount());
                object.put(type, jsonArray);
            }
        }
        JSONArray array=new JSONArray();
        for (int i = daySplit; i >0 ; i--) {
            array.add(DateUtils.dateToStr(DateUtils.beforDate(time, i),"yy年M月d日"));
        }
        data.put("values", object);
        data.put("times", array);
        return new QaResult(data);
    }


    @RequestMapping(value = "questionBiLine", method = RequestMethod.POST)
    @ResponseBody
    public QaResult questionBiLine() {
        Date time = new Date();
        int daySplit = 7;
        String dateStartTime = DateUtils.dateToStr(DateUtils.beforDate(time, daySplit));
        String dateEndTime = DateUtils.dateToStr(DateUtils.beforDate(time, 0));
        List<QuestionTypeBi> typeList = questionTypeBiMapper
                .selectCountByDays(new HashMap() {
                    {
                        put("startTime", dateStartTime);
                        put("endTime", dateEndTime);
                    }
                });
        Collections.sort(typeList, new QuestionTypeBiCompare());
        JSONObject data = new JSONObject();
        JSONObject object = new JSONObject();
        JSONArray questionCount=new JSONArray();
        JSONArray answerCount=new JSONArray();
        for (QuestionTypeBi bi : typeList) {
            questionCount.add(bi.getQuestionCount());
            answerCount.add(bi.getAnswerCount());
        }
        object.put("问题数",questionCount);
        object.put("回答数",answerCount);
        JSONArray array=new JSONArray();
        for (int i = daySplit; i >0 ; i--) {
            array.add(DateUtils.dateToStr(DateUtils.beforDate(time, i),"yy年M月d日"));
        }
        data.put("values", object);
        data.put("times", array);
        return new QaResult(data);
    }


}
