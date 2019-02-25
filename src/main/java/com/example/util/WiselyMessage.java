package com.example.util;

/**
 * Created by 李印锋 on 2019/2/21 11:23
 * 接收前端消息实体
 */


public class WiselyMessage {
    private String name;
    private String id;
    private String msg;

    public WiselyMessage() {

    }

    public WiselyMessage(String msg) {
        this.msg = msg;
    }

    public WiselyMessage(String name, String id, String msg) {
        this.name = name;
        this.id = id;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
