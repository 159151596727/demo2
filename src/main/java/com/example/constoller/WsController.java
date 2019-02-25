package com.example.constoller;

import com.example.entity.Auuser;
import com.example.util.Constant;
import com.example.util.DataMaps;
import com.example.util.WiselyMessage;
import com.example.wsService.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李印锋 on 2019/2/22 16:24
 */
@Controller
public class WsController {
    @Resource
    WebSocketService webSocketService;


    @MessageMapping(Constant.FORETOSERVERPATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
   // @SendTo(Constant.PRODUCERPATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public void say(WiselyMessage message){
        //webSocketService.sendMsg(new WiselyMessage("（群发）客户端返回浏览器的信息：" + message.getName() + "!"));
        List<String> users = new ArrayList<>();
        users.add(message.getId());
        users.add(message.getOwnId());
        webSocketService.sendUsers(users,new WiselyMessage(message.getName() + "：" + message.getMsg()));
    }
}
