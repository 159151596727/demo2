<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="localjs/jquery-3.3.1.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>
    <script src="localjs/login.js" type="text/javascript"></script>
    <script src="layui/layui.all.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>
<div class="loginbody">
    <span class="systemlogo"></span>
    <div class="loginbox">
        <ul>
            <li><input name="loginCode" type="text"  placeholder="请输入用户名" class="loginuser" id="name" value=""/></li>
            <li><input name="password" type="password" placeholder="请输入密码" class="loginpwd" id="password" value=""/></li>
            <li><!-- 后面添加参数起到清除缓存作用 -->
                <img style="margin-left: 80px" name="imgCode" id="imgCode" title="点击更换验证码" alt="验证码" onclick="this.src='/kaptcha?d='+new Date()*1" src="/kaptcha"/></li>
            <li><input name="tryCode" type="text" placeholder="请输入验证码" class="loginpwd" id="tryCode" value=""/></li>
            <li><input name="btoOk" id="btoOk" type="submit" class="loginbtn" value="登录"/>
                <input name="register" id="register" type="button" onclick="location.href='register';" class="loginbtn" value="注册"/>
                <%--<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>--%>
                <label><a href="#">忘记密码？</a></label></li>
        </ul>
    </div>
</div>
<div class="loginbm">个人版权所有</div>
</body>
</html>
