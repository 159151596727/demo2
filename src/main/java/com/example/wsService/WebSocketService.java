package com.example.wsService;

import com.example.util.Constant;
import com.example.util.WiselyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李印锋 on 2019/2/22 16:41
 */
@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(WiselyMessage msg) {
        template.convertAndSend(Constant.PRODUCERPATH, msg);
    }


    /**
     * 发给订阅了相关频道的在线用户
     *
     * @param msg
     */
    public void sendMsg(String sendTo,WiselyMessage msg) {
        template.convertAndSend(sendTo, msg);
    }

    /**
     * 发送给指定用户
     * @param users
     * @param msg
     */
    public void sendUsers(List<String> users, WiselyMessage msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, Constant.P2PPUSHPATH, msg);
        });
    }
}
