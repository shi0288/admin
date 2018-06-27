package com.xyauto.qa.mapper;

import com.xyauto.qa.entity.QuestionCar;
import com.xyauto.qa.util.BaseMapper;

public interface QuestionCarMapper extends BaseMapper<QuestionCar> {

    int saveQuestionCar(QuestionCar questionCar);
}