package com.xyauto.qa.cons;

/**
 * Created by shiqm on 2017-05-17.
 */
public enum LogAction {

    
    QUESTION_SET_GOOD(1, "精华操作", LogModule.QUESTION),
    QUESTION_SET_TOP(2, "置顶操作", LogModule.QUESTION),
    QUESTION_ADD_QUESTION(3, "创建问题", LogModule.QUESTION),
    QUESTION_DELETE_QUESTION(4,"删除问题",LogModule.QUESTION),
    QUESTION_UPDATE_QUESTION(5,"更新问题",LogModule.QUESTION),
    QUESTION_RECOVERY_QUESTION(6,"恢复问题",LogModule.QUESTION),
    QUESTION_VERIFY_QUESTION(10,"恢复问题",LogModule.QUESTION),
    
    ANSWER_CREATE_ANSWER(7,"创建回复",LogModule.ANSWER),
    ANSWER_DELETE_ANSWER(8,"删除回复",LogModule.ANSWER),
    ANSWER_RECOVER_ANSWER(9,"删除回复",LogModule.ANSWER),
    
    FROZEN_USER(1,"封禁用户",LogModule.USER),
    SETMODEL_USER(2,"设置标兵",LogModule.USER),
    SETEXPERT_USER(3,"设置专家",LogModule.USER),
    SETADVISER_USER(4,"设置车顾问",LogModule.USER),
    USER_LOGIN(11,"登录",LogModule.USER),
    
    NONE(0, "其他", LogModule.NONE);
    
    private Integer value;
    private String name;
    private LogModule logModule;

    private LogAction(Integer value, String name, LogModule logModule) {
        this.value = value;
        this.name = name;
        this.logModule = logModule;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public LogModule getLogModule() {
        return logModule;
    }
}
