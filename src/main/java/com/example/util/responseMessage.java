package com.example.util;

/**
 * Created by 李印锋 on 2019/2/21 11:19
 *
 * 后台发送消息实体
 */
public class responseMessage {
    private String content;

    public responseMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
