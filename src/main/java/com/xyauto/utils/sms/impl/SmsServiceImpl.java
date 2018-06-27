package com.xyauto.utils.sms.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.qa.core.annotation.Log;
import com.xyauto.utils.sms.ISmsService;
import com.xyauto.utils.sms.vo.EmailVo;

@Service
public class SmsServiceImpl implements ISmsService{
	@Autowired
	private SendEmailService emailService;
	@Log
	protected Logger logger;
	
	@Override
	public void sendEmailAli(EmailVo emailVo) {
		try {
			emailService.createEmailAli(emailVo);
		} catch (Exception e) {
			logger.error("发送邮件失败："+e);
			e.printStackTrace();
		}		
	}
	
}
