package com.xyauto.qa.service.impl;

import com.xyauto.qa.entity.AdminLogs;
import com.xyauto.qa.mapper.AdminLogsMapper;
import com.xyauto.qa.service.AdminLogsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017-05-17.
 */

@Service
public class AdminLogsServiceImpl extends ABaseServiceImpl<AdminLogs, Integer> implements AdminLogsService{

	@Autowired
	private AdminLogsMapper adminLogsMapper;
	@Override
	public AdminLogs selectfrozenUser(Long uid) {		
		return adminLogsMapper.selectfrozenUser(uid);
	}

}
