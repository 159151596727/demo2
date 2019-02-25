package com.example.util;

/**
 * Created by 李印锋 on 2019/2/21 11:23
 * 接收前端消息实体
 */


public class WiselyMessage {
    private String name;

    public WiselyMessage() {

    }

    public WiselyMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
