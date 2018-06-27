package com.xyauto.utils.sms.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.xyauto.utils.sms.vo.EmailVo;

@Service
public class SendEmailService {
	// 设置发件人邮箱账号密码
	public static String myEmailAccount = "wenda@xingyuanauto.com";
	public static String myEmailPassword = "Ask@12345";
	// 设置发件邮箱的服务器地址
	public static String myEmailSMTPHost = "smtp.mxhichina.com";
	// 设置邮箱域名
	public static String emailDomain="@xingyuanauto.com";
	void createEmailAli(EmailVo emailVo) throws Exception {
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		// 创建回话
		Session session = Session.getDefaultInstance(props);
		// 创建邮件
		emailVo.setSendMailUser(myEmailAccount);
		MimeMessage message = createEmail(session,  emailVo);
		// 根据session获取邮件传输对象
		Transport transport = session.getTransport();
		transport.connect(myEmailAccount, myEmailPassword);
		// 发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();
	}

	MimeMessage createEmail(Session session, EmailVo emailVo) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);		
		String[] receiveMailToUser = emailVo.getReceiveMailToUser();
		String[] receiveMailCCUser = emailVo.getReceiveMailCCUser();
		String[] receiveMailBCCUser = emailVo.getReceiveMailBCCUser();
		message.setFrom(new InternetAddress(emailVo.getSendMailUser(), "行圆问答",
				"UTF-8"));
		if (receiveMailToUser != null && receiveMailToUser.length > 0) {
			for (int i = 0; i < receiveMailToUser.length; i++) {
				message.addRecipient(MimeMessage.RecipientType.TO,
						new InternetAddress(receiveMailToUser[i]+emailDomain, null,"UTF-8"));
			}
		}
		if (receiveMailCCUser != null && receiveMailCCUser.length > 0) {
			for (int i = 0; i < receiveMailCCUser.length; i++) {
				message.addRecipient(MimeMessage.RecipientType.CC,
						new InternetAddress(receiveMailCCUser[i]+emailDomain, null,"UTF-8"));
			}
		}
		if (receiveMailBCCUser!=null&& receiveMailBCCUser.length>0) {
			for (int i = 0; i < receiveMailBCCUser.length; i++) {
				message.addRecipient(MimeMessage.RecipientType.BCC,
						new InternetAddress(receiveMailBCCUser[i]+emailDomain,null,"UTF-8"));
			}
		}
		message.setSubject(emailVo.getSubject(), "UTF-8");		
        //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
        Multipart mainPart = new MimeMultipart();  
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        //设置HTML内容  
        messageBodyPart.setContent(emailVo.getContent(),"text/html; charset=utf-8");  
        mainPart.addBodyPart(messageBodyPart);  
        message.setContent(mainPart);  
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

}
