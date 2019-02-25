<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>注册</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="localjs/jquery-3.3.1.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="localjs/register.js" type="text/javascript"></script>
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
    <span>欢迎z注册后台管理界面平台</span>
    <ul>
        <li><a href="#" onClick="javascript :history.back(-1);">登录</a></li>
    </ul>
</div>
<div class="loginbody">
    <span class="systemlogo"></span>
    <div class="registerbox">
        <%--action="register.do" method="post"--%>
        <form id="form1" name="form1" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="loginCode" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password"  placeholder="请输入密码" autocomplete="off"
                           onkeyup="showStrength(this.value)" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" id="passwordStrength"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="rePassword" placeholder="请再次输入密码" autocomplete="off"
                           onkeyup="isRePassword(this.value)" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" id="rePasswordStrength"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block" >
                    <input type="text" name="mobile"  placeholder="请输入手机号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="code" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                </div>
                <input name="getCode" id="getCode" type="button" class="layui-btn layui-btn-normal" style="margin-left: -80px"
                       value="获取验证码" onclick="sendemail()"/>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked>
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>



            <%--<div class="layui-form-item">
                <label class="layui-form-label">选择框</label>
                <div class="layui-input-block">
                    <select name="city" lay-verify="" lay-search>
                        <option value="">请选择一个城市</option>
                        <option value="010">layer</option>
                        <option value="021">form</option>
                        <option value="0571">layim</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">复选框</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="like[write]" title="写作">
                    <input type="checkbox" name="like[read]" title="阅读" checked>
                    <input type="checkbox" name="like[dai]" title="发呆">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开关</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="switch" lay-skin="switch">
                </div>
            </div>--%>




           <%-- <div class="layui-collapse" lay-accordion="" style="margin-left: 100px;border: none;">
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">更多信息</h2>
                    <div class="layui-colla-content"style="width: 500px;margin-left: -110px;">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="userName"  placeholder="请输入姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="radio" name="sex" value="男" title="男" checked>
                                <input type="radio" name="sex" value="女" title="女">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" name="email" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户住址</label>
                            <div class="layui-input-block">
                                <input type="text" name="userAddress"  placeholder="请输入当前住址" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>--%>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" >立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>

    </div>
</div>
</body>
</html>
