package com.example.util;

/**
 * Created by 李印锋 on 2019/2/21 11:23
 * 接收前端消息实体
 */


public class WiselyMessage {
    private String name;//发送人的用户名
    private String ownId;//发送人的id
    private String id;//接收人的id
    private String msg;//返回或者发送的信息

    public WiselyMessage() {

    }

    public WiselyMessage(String msg) {
        this.msg = msg;
    }
    public WiselyMessage(String ownId,String msg) {
        this.ownId = ownId;
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
