<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>聊天</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/reset.min.css">
    <link rel="stylesheet" href="css/indexStyle.css">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">

</head>
<body onload="connect();">
<noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
<div class="wrapper">
    <div class="container">
        <div class="left">
            <div class="top">
                <input type="text" placeholder="Search"/>
                <a href="javascript:;" class="search"></a>
            </div>
            <ul class="people">
                <c:forEach items="${auusers }" var="user" varStatus="status">
                    <li class="person" id="li${user.id }" data-chat="person${user.id }">
                        <img src="images/img14.png" alt=""/>
                        <span class="name">${user.userName }</span>
                        <span class="time">1:44 PM</span>
                        <span class="preview">显示最后一句聊天记录</span>
                        <c:if test="${ status.index != 0}">
                            <span id="icon${user.id }" class="layui-badge-dot"></span>
                        </c:if>
                        <input class="id" value="${user.id }" type="hidden"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="right">
            <div class="top"><span>To: <span class="name" id="showName"></span></span></div>
            <c:forEach items="${auusers }" var="user">
                <div class="chat" id="message${user.id}" data-chat="person${user.id }">
                    <div class="conversation-start">
                        <span>Today, 5:38 PM</span>
                    </div>
                    <c:forEach items="${chatrecords }" var="chatrecord">
                        <c:if test="${user.id == chatrecord.sender}">
                            <div class="bubble you">
                                    ${chatrecord.sendMessage }
                            </div>
                        </c:if>
                        <c:if test="${user.id == chatrecord.receiver}">
                            <div class="bubble me">
                                    ${chatrecord.sendMessage }
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:forEach>
            <div class="write">
                <a href="javascript:;" class="write-link attach"></a>
                <input type="text" id="msg"/>
                <a href="javascript:;" class="write-link smiley"></a>
                <a href="javascript:;" onclick="sendMsg();" name="sendMsg" class="write-link send"></a>
            </div>
        </div>
    </div>
</div>

<script src="localjs/jquery-3.3.1.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script src="js/indexStyle.js"></script>
<script src="js/sockjs.min.js"></script>
<script src="js/stomp.min.js"></script>
<script th:inline="javascript">
    let stompClient = null;
    let userId = ${loginUser.id}; //登录用户的id
    let userName = "${loginUser.userName}"; //登录用户的用户名
    //初始化接收信息人的id
    let id = $("ul.people").find("input.id:first").val();
    //点击切换接收信息人的id
    $("ul.people").on("click", "li", function () {
        id = $(this).find("input.id").val();
        $("span#icon" + id).attr("class", "");//清除消息通知的小图标
    });
    //初始化添加发送人
    $("#showName").text($("ul.people").find("span.name:first").text());

    function connect() {
        let socket = new SockJS('/endpointWisely'); //1连接SockJS的endpoint是“endpointWisely”，与后台代码中注册的endpoint要一样。
        stompClient = Stomp.over(socket);//2创建STOMP协议的webSocket客户端。
        stompClient.connect({}, function (frame) {//3连接webSocket的服务端。
            console.log('开始进行连接Connected: ' + frame);
            //订阅个人推送的服务目标
            stompClient.subscribe('/user/' + userId + '/msg', function (respnose) {
                console.log(respnose);
                showResponse(JSON.parse(respnose.body).ownId, JSON.parse(respnose.body).msg);
            });
        });
    }

    //关闭连接
    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    //发送信息
    function sendMsg() {
        let msg = $("#msg").val();
        stompClient.send("/ws-push/welcome", {}, JSON.stringify({
            'name': userName,
            'ownId': userId,
            'id': id,
            'msg': msg
        }));
        $("#message" + id).append("<div class=\"bubble me\">" + $("#msg").val() + "</div>")
        $("#msg").val("");
    }

    //接收返回发送人的id以及信息并输入到聊天框
    function showResponse(ownId,message) {
        $("#message" + ownId).append("<div class=\"bubble you\">" + message + "</div>");
        if (ownId != id) {
            $("#li"+ownId).append(" <span id=\"icon"+ownId+"\" class=\"layui-badge-dot\"></span>");
        }
    }
</script>

</body>
</html>
