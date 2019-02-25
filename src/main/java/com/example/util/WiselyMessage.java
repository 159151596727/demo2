package com.example.util;

/**
 * Created by 李印锋 on 2019/2/21 11:23
 * 接收前端消息实体
 */


public class WiselyMessage {
    private String name;
    private String ownId;
    private String id;
    private String msg;

    public WiselyMessage() {

    }

    public WiselyMessage(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
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
