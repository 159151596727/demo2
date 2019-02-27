package com.example.entity;

import java.sql.Timestamp;

/**
 * Created by 李印锋 on 2/27/0027 17:01
 * 聊天记录实体类
 */
public class Chatrecord {
    private Integer id;
    private Integer sender;
    private String sendMessage;
    private Integer receiver;
    private Timestamp createData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Timestamp getCreateData() {
        return createData;
    }

    public void setCreateData(Timestamp createData) {
        this.createData = createData;
    }

    public Chatrecord() {
    }

    public Chatrecord(Integer sender, String sendMessage, Integer receiver) {
        this.sender = sender;
        this.sendMessage = sendMessage;
        this.receiver = receiver;
    }
}
