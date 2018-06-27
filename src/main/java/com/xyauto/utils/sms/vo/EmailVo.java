package com.xyauto.utils.sms.vo;

/**
 * 邮件类
 * @author zhangbh
 *
 */
public class EmailVo {
	//发件人
	private String sendMailUser;
	//收件人
	private String[] receiveMailToUser;
	//抄送
	private String[] receiveMailCCUser;
	//密送
	private String[] receiveMailBCCUser;
	//正文
	private String content;
	//主题
	private String subject;	
	public String getSendMailUser() {
		return sendMailUser;
	}
	public void setSendMailUser(String sendMailUser) {
		this.sendMailUser = sendMailUser;
	}
	public String[] getReceiveMailToUser() {
		return receiveMailToUser;
	}
	public void setReceiveMailToUser(String[] receiveMailToUser) {
		this.receiveMailToUser = receiveMailToUser;
	}
	public String[] getReceiveMailCCUser() {
		return receiveMailCCUser;
	}
	public void setReceiveMailCCUser(String[] receiveMailCCUser) {
		this.receiveMailCCUser = receiveMailCCUser;
	}
	public String[] getReceiveMailBCCUser() {
		return receiveMailBCCUser;
	}
	public void setReceiveMailBCCUser(String[] receiveMailBCCUser) {
		this.receiveMailBCCUser = receiveMailBCCUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
