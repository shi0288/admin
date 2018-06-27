package com.xyauto.qa.cons;

/**
 * Created by shiqm on 2017-05-17.
 */
public enum LogModule {

    NONE(0,"其他"),
    QUESTION(1,"问题"),
    ANSWER(2,"回答"),
    USER(3,"用户");

    private  Integer value;
    private  String name;

    private LogModule(Integer value,String name) {
        this.value = value;
        this.name = name;
    }
    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
