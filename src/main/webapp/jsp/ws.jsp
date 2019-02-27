<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Spring Boot+WebSocket+广播式</title>

</head>
<body onload="disconnect()">
<noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div id="conversationDiv">
        <label>输入要发送的消息</label><input type="text" id="msg" />
        <label>要发送的用户id</label><input type="text" id="id" />
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
        <p id="response1"></p>
    </div>
</div>
<script src="js/sockjs.min.js"></script>
<script src="js/stomp.min.js"></script>
<script src="localjs/jquery-3.3.1.js"></script>
<script th:inline="javascript">
    let stompClient = null;
    //此值有服务端传递给前端,实现方式没有要求
    let userId = ${loginUser.id};
    let userName = "${loginUser.userName}";

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }

    function connect() {
        let socket = new SockJS('/endpointWisely'); //1连接SockJS的endpoint是“endpointWisely”，与后台代码中注册的endpoint要一样。
        stompClient = Stomp.over(socket);//2创建STOMP协议的webSocket客户端。
        stompClient.connect({}, function(frame) {//3连接webSocket的服务端。
            setConnected(true);
            console.log('开始进行连接Connected: ' + frame);
            //订阅全部推送的服务目标
            stompClient.subscribe('/topic/getResponse', function(respnose){
                showResponse("/topic/getResponse" + JSON.parse(respnose.body).msg);
            });
            //订阅个人推送的服务目标
            stompClient.subscribe('/user/' + userId + '/msg', function(respnose){
                console.log(respnose);
                showResponse1(JSON.parse(respnose.body).msg);
            });
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        let msg = $('#msg').val();
        let id = $('#id').val();
        if(msg == null){
            return;
        }
        //通过stompClient.send（）向地址为"/welcome"的服务器地址发起请求，与@MessageMapping里的地址对应。因为我们配置了registry.setApplicationDestinationPrefixes(Constant.WEBSOCKETPATHPERFIX);所以需要增加前缀/ws-push/
        stompClient.send("/ws-push/welcome", {}, JSON.stringify({ 'name': userName,'ownId':userId,'id':id,'msg':msg }));
    }

    function showResponse(message) {
        let response = $("#response");
        response.html(response.html()+message+ '<br/>');
    }
    function showResponse1(message) {
        let response = $("#response1");
        response.html(response.html()+message+ '<br/>');
    }
</script>
</body>
</html>
