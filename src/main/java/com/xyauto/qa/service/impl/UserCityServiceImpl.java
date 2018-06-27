package com.xyauto.qa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.qa.entity.UserCity;
import com.xyauto.qa.mapper.UserCityMapper;
import com.xyauto.qa.service.IUserCityService;

@Service
public class UserCityServiceImpl extends ABaseServiceImpl<UserCity, Long>
		implements IUserCityService {
}
