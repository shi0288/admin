package com.xyauto.qa.core.aop;

import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.core.annotation.QaLog;
import com.xyauto.qa.entity.AdminLogs;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.service.AdminLogsService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by shiqm on 2017-05-17.
 */

@Component
@Aspect
public class QaLogAop {


    @Resource
    private HttpSession httpSession;

    @Resource
    private AdminLogsService adminLogsService;


    @AfterReturning(value = "within(com.xyauto.qa.controller..*) && @annotation(qaLog)", returning = "result")
    public void addLogSuccess(JoinPoint jp, QaLog qaLog, Object result) {
        String userName = (String) this.httpSession.getAttribute(CommonCons.Session_Flag.ACCOUNT);
        if (StringUtils.isEmpty(userName)) {
            return;
        }
        AdminLogs adminLogs = new AdminLogs();
        adminLogs.setUsername(userName);
        adminLogs.setModule(qaLog.action().getLogModule().getValue());
        adminLogs.setAction(qaLog.action().getValue());
        adminLogs.setCreated_at(DateUtils.getTimes());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(qaLog.content());
        if (!"".equals(qaLog.cmd())) {
            stringBuffer.append(logFactory(qaLog.cmd(), jp.getArgs(), result));
        }
        adminLogs.setContent(stringBuffer.toString());
        adminLogsService.save(adminLogs);
    }

    public String logFactory(String cmd, Object[] objects, Object result) {
        QaResult qaResult = null;
        switch (cmd) {
            case "setGood":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    boolean _is = (boolean) objects[1];
                    if (_is) {
                        return "问题【" + question.getQuestionId() + "】设置了精华";
                    } else {
                        return "问题【" + question.getQuestionId() + "】取消了精华";
                    }
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "setTop":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    boolean _is = (boolean) objects[1];
                    if (_is) {
                        return "问题【" + question.getQuestionId() + "】设置了置顶，权重：" + question.getTopSort();
                    } else {
                        return "问题【" + question.getQuestionId() + "】取消了置顶";
                    }
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "createQuestion":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    String uid = (String) objects[0];
                    String content = (String) objects[2];
                    if (content.length() > 20) {
                        content = content.substring(0, 20)+"...";
                    }
                    return "选择的运营ID为【" + uid + "】，内容【" + content + "】";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "deleteQuestion":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    return "问题id："+question.getQuestionId()+"被删除了";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "recoveryQuestion":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    return "问题id："+question.getQuestionId()+"被恢复了";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                } 
            case "updateQuestion":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    return "问题id："+question.getQuestionId()+"被更新了";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                } 
            case "verifyQuestion":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Question question = (Question) objects[0];
                    return "问题id："+question.getQuestionId()+"审核通过";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "createAnswer":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                    Answer answer = (Answer) objects[0];
                    String content = answer.getContent();
                    if (content.length() > 20) {
                        content = content.substring(0, 20)+"...";
                    }
                    return "问题id："+answer.getQuestionId()+"增加回复内容"+content;
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "deleteAnswer":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                	Long answerId =  (Long)objects[0];
                    return "回复id："+answerId+"被删除了";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "recoverAnswer":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                	Long answerId =  (Long)objects[0];
                    return "回复id："+answerId+"被恢复了";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "login":
                qaResult = (QaResult) result;
                if (ErrorCode.OK.equals(qaResult.getCode())) {
                	
                    return "用户登录成功";
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }                
            case "frozenUser":
            	qaResult = (QaResult) result;
            	if (ErrorCode.OK.equals(qaResult.getCode())) {
                	Long uid =  (Long)objects[0];                	
                	Boolean b=(Boolean)objects[1];
                	if (b!=null&&!b) {
                		return "用户："+uid+"被解禁";
					}else{
						return "用户："+uid+"被封禁";
					}                   
                } else {
                    return "操作失败：" + qaResult.getMessage();
                }
            case "setModel":
            	qaResult = (QaResult) result;
            	if (ErrorCode.OK.equals(qaResult.getCode())) {
            		User user =  (User)objects[0];                	                	
            		return "设置标兵成功："+user.getUid();             
                } else {
                    return "设置标兵失败：" + qaResult.getMessage();
                }
            case "setExpert":
            	qaResult = (QaResult) result;
            	if (ErrorCode.OK.equals(qaResult.getCode())) {
            		User user =  (User)objects[0];                	                	
            		return "设置专家成功："+user.getUid();             
                } else {
                    return "设置专家失败：" + qaResult.getMessage();
                }
            case "setAdviser":
            	qaResult = (QaResult) result;
            	if (ErrorCode.OK.equals(qaResult.getCode())) {
            		User user =  (User)objects[0];                	                	
            		return "设置车顾问成功："+user.getUid();             
                } else {
                    return "设置车顾问失败：" + qaResult.getMessage();
                }
            default:
                return "";
        }
    }



}
