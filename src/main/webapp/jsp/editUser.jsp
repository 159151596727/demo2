<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/11
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑用户</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="localjs/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="addressAPI/js/distpicker.data.js"></script>
    <script src="addressAPI/js/distpicker.js"></script>
    <script src="addressAPI/js/main.js"></script>

<%--<script language="JavaScript" src="localjs/editUser.js"></script>--%>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-image: url(images/light.png);">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>账号设置</legend>
</fieldset>
<div class="loginbody">

    <form class="form-inline">
        <div data-toggle="distpicker">
            <div class="form-group">
                <label class="sr-only" for="province1">Province</label>
                <select class="form-control" id="province1"></select>
            </div>
            <div class="form-group">
                <label class="sr-only" for="city1">City</label>
                <select class="form-control" id="city1"></select>
            </div>
            <div class="form-group">
                <label class="sr-only" for="district1">District</label>
                <select class="form-control" id="district1"></select>
            </div>
        </div>
    </form>


    <div class="edituserbox" id="layerDemo">
        <div style="margin-top: 10px;">
            <button data-method="notice" class="layui-btn layui-btn-primary layui-btn-fluid" name="userName">用户名：${loginUser.userName}</button>
        </div>
        <div style="margin-top: 10px;">
            <button class="layui-btn layui-btn-primary layui-btn-fluid" name="password">密码：${loginUser.password}</button>
        </div>
        <div style="margin-top: 10px;">
            <button class="layui-btn layui-btn-primary layui-btn-fluid" name="mobile">手机号：${loginUser.mobile}</button>
        </div>
        <div style="margin-top: 10px;">
            <button class="layui-btn layui-btn-primary layui-btn-fluid" name="sex">性别：${loginUser.sex}</button>
        </div>
        <div style="margin-top: 10px;">
            <button class="layui-btn layui-btn-primary layui-btn-fluid" name="userAddress">地址：${loginUser.userAddress}</button>
        </div>
        <div style="margin-top: 10px;">
            <button data-method="notice" class="layui-btn layui-btn-primary layui-btn-fluid" name="email">邮箱：${loginUser.email}</button>
        </div>
    </div>
</div>


<script>
    function getText(str){
       return str.split("：")[0];
    }
    function getValues(str){
        return str.split("：")[1];
    }

    layui.use('layer', function(){
        var $ = layui.jquery, layer = layui.layer;
        //触发事件
        var active = {
            notice: function(){
                //示范一个公告层
                layer.open({
                    type: 1
                    ,title: "修改"+getText($(this).html()) //不显示标题栏
                    ,closeBtn: false
                    ,area: '450px;'
                    ,shade: 0.8
                    ,id: 'notice' //设定一个id，防止重复弹出
                    ,btn: ['确定', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div class="layui-form-item" style="width: 400px;margin-top: 10px;">\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input style="margin-left: -20px;" type="text" name="values" placeholder="请输入'+getText($(this).html())+ '" value="'+getValues($(this).html())+ '" autocomplete="off" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>'
                    ,yes: function(index,layero){
                        layer.close(index);
                        var  str = "${loginUser.userName}";
                        layer.msg(str);
                    }
                });
            }
        };

        $('#layerDemo .layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
</script>

</body>
</html>
