package com.xyauto.qa.cons;

/**
 * Created by shiqm on 2017/3/17.
 */
public enum ViewKeyCons {

    //+---------------------------------------------------
    //| 专家标兵管理
    //+---------------------------------------------------
    MANAGE_LIST_SPECIAL("manage/specialListCom","pager"),
    MANAGE_LIST_SPECIAL_MODEL("manage/specialModelListCom","pager"),
    MANAGE_LIST_SPECIAL_add("business/adverHomeCom","pager"),
    MANAGE_LIST_AdvisorHome_add("business/advisorHomeCom","pager");


    ViewKeyCons(String viewName, String key) {
        this.viewName = viewName;
        this.key = key;
    }

    private String viewName;

    private String key;


    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
