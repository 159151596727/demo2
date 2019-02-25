var countdown=60;//验证码计时60秒
var codeIsNull=false;//判断验证码是否已经发送
var nameIsExist = true;//验证用户是否存在
var mobileIsExist = true;//验证手机号是否存在
var  nameTipsList = new  Array();//保存用户名提示框的提示信息的下标集合
var  passwordTipsList = new  Array();//保存密码提示框的提示信息的下标集合
var  mobileTipsList = new  Array();//保存手机号提示框的提示信息的下标集合



/**
 * 验证用户名
 * @returns {boolean}
 */
function validataName() {
    if(!(/^[a-zA-Z][a-zA-Z0-9]{1,9}$/.test($("input[name='loginCode']").val()))){
        var tips = layer.tips('请输入正确的用户名！', "input[name='loginCode']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
        nameTipsList.push(tips);
        return false;
    }
    for (var i = 0; i < nameTipsList.length; i++){
        layer.close(nameTipsList[i]);
    }
    nameTipsList.length = 0;//清空数组
    return true;
}

/**
 * 验证用户名是否存在
 * @returns {boolean}
 */
function validataNameIsExist() {
    if (nameIsExist){
        var tips = layer.tips('该用户已存在，请更换其他用户名！', "input[name='loginCode']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
        nameTipsList.push(tips);
        return true;
    }
    return false;
}

/**
 * 验证手机号是否存在
 * @returns {boolean}
 */
function validataMobileIsExist() {
    if (mobileIsExist){
        var tips = layer.tips('该手机号已注册过账号！', "input[name='mobile']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
        mobileTipsList.push(tips);
        return true;
    }
    return false;
}

/**
 * 验证密码
 * @returns {boolean}
 */
function validataPassword() {
    if(!(/^[\w_-]{6,16}$/.test($("input[name='password']").val()))){
        var tips = layer.tips('请输入正确的密码！', "input[name='password']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
        passwordTipsList.push(tips);
        return false;
    }
    for (var i = 0; i < passwordTipsList.length; i++){
        layer.close(passwordTipsList[i]);
    }
    passwordTipsList.length = 0;//清空数组
    return true;
}

/**
 * 验证手机号
 * @returns {boolean}
 */
function validataMobile() {
    if(!(/^1[34578]\d{9}$/.test($("input[name='mobile']").val()))){
        var tips = layer.tips('请输入正确的手机号码！', "input[name='mobile']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
        mobileTipsList.push(tips);
        return false;
    }
    for (var i = 0; i < mobileTipsList.length; i++){
        layer.close(mobileTipsList[i]);
    }
    mobileTipsList.length = 0;//清空数组
    return true;
}

/**
 * 验证验证码
 * @returns {boolean}
 */
function validataCode() {
    if("" == $("input[name='code']").val() || null == $("input[name='code']").val()){
        layer.tips('验证码不能为空！', "input[name='code']", {
            tips: [2, '#A4D3EE'],
            time: 2000,
            tipsMore: true
        });
        return false;
    }
    return true;
}



function validaRegister() {
    //用户名
    var name = $("input[name='loginCode']");
    var namedTips = -1;
    name.focus(function () {
        for (var i = 0; i < nameTipsList.length; i++){
            layer.close(nameTipsList[i]);
        }
        namedTips = layer.tips('请输入2-10个字符的英文，数字，下划线或者其组合！', "input[name='loginCode']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
    });
    name.blur(function () {
        layer.close(namedTips);
        if(validataName()){
            $.ajax({
                type: "post",//请求类型
                url: "validataName.do",//请求的url
                data: {"name": $("input[name='loginCode']").val()},
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {
                    if (data == "ok") {
                        for (var i = 0; i < nameTipsList.length; i++){
                            layer.close(nameTipsList[i]);
                        }
                        nameTipsList.length = 0;//清空数组
                        nameIsExist = false;
                    }else {
                        var tips = layer.tips('该用户名已存在，请更换其他用户名！', "input[name='loginCode']", {
                            tips: [2, '#A4D3EE'],
                            time: 0,
                            tipsMore: true
                        });
                        nameTipsList.push(tips);
                        nameIsExist = true;
                    }
                },
                error: function () {
                    layer.msg("服务器连接异常！请联系系统管理员");
                }
            });
        }

    });

    //密码
    var password = $("input[name='password']");
    var passwordTips = -1;
    password.focus(function () {
        for (var i = 0; i < passwordTipsList.length; i++){
            layer.close(passwordTipsList[i]);
        }
        passwordTips = layer.tips('请输入6-16个字符的英文，数字，下划线或者其组合！', "input[name='password']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
    });
    password.blur(function () {
        layer.close(passwordTips);
        validataPassword();
    });

    //确认密码
    /*var rePassword = $("input[name='rePassword']");
    rePassword.focus(function () {
        layer.tips('请确认与上方密码一致！', "input[name='rePassword']", {
            tips: [2, '#A4D3EE'],
            time: 0
        });
    });
    rePassword.blur(function () {
        if (rePassword.val() == null || "" == (rePassword.val())) {
            layer.tips('密码不能为空', "input[name='rePassword']", {
                tips: [2, '#A4D3EE'],
                time: 0
            });
        }else {
            layer.close(layer.tips());
        }
    });*/

    //手机号
    var mobileTips = -1;
    var mobile = $("input[name='mobile']");
    mobile.focus(function () {
        for (var i = 0; i < mobileTipsList.length; i++){
            layer.close(mobileTipsList[i]);
        }
        mobileTips = layer.tips('请输入11位的手机号码！', "input[name='mobile']", {
            tips: [2, '#A4D3EE'],
            time: 0,
            tipsMore: true
        });
    });
    mobile.blur(function () {
        layer.close(mobileTips);
        if(validataMobile()){
            $.ajax({
                type: "post",//请求类型
                url: "validataMobile.do",//请求的url
                data: {"mobile": $("input[name='mobile']").val()},
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {
                    if (data == "ok") {
                        for (var i = 0; i < mobileTipsList.length; i++){
                            layer.close(mobileTipsList[i]);
                        }
                        mobileTipsList.length = 0;//清空数组
                        mobileIsExist = false;
                    }else {
                        var tips = layer.tips('该手机号已注册过账号！', "input[name='mobile']", {
                            tips: [2, '#A4D3EE'],
                            time: 0,
                            tipsMore: true
                        });
                        mobileTipsList.push(tips);
                        mobileIsExist = true;
                    }
                },
                error: function () {
                    layer.msg("服务器连接异常！请联系系统管理员");
                }
            });
        }
    });

    //验证码
    var code = $("input[name='code']");
    code.focus(function () {
    });
    code.blur(function () {
        validataCode();
    });



}


/**
 * 发送验证码倒计时
 * @param obj
 */
function settime(obj) {
    if (countdown == 0) {
        obj.attr('disabled',false);
        obj.val("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.attr('disabled',true);
        obj.val("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
            settime(obj) }
        ,1000);
}

$(function () {
    validaRegister();
    $("#form1").submit(function () {
        if (!dataCheck()) {
            return false;
        }
        if (!codeIsNull){
            layer.msg("服务器未能收到验证码，请稍后再次获取验证码！");
            return false;
        }
        $.ajax({
            type: "post",//请求类型
            url: "register.do",//请求的url
            data: $("#form1").serialize(),
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {
                if (data == "ok") {
                    location.href = "main.html";
                }else if (data == "code"){
                    layer.msg("验证码错误！");
                } else if (data == "null") {
                    layer.msg("数据接收异常！");
                }else {
                    layer.msg("注册失败！");
                }
            },
            error: function () {
                layer.msg("服务器连接异常！请联系系统管理员");
            }
        });
        return false;   //防止表单自动提交
    });
});
function dataCheck(){
    if (!validataName() || validataNameIsExist()) {
        return false;
    }else if (!validataPassword()){
        return false;
    } else if (!isRePassword($("input[name='rePassword']").val())){
        return false;
    } else if (!validataMobile()){
        return false;
    } else if (!validataCode()){
        return false;
    }
    return true;
}
/**
 * 表单提交
 */
layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        //return dataCheck();
        return false;
    });
});
layui.use(['element', 'layer'], function(){

});


/**
 * 判断密码强度
 * @param password
 * @returns {number}
 */
function getPasswordStrength(password){
    var strength=0;
    $([/.{6,}/,/[0-9]+/,/[a-z]+/,/[A-Z]+/,/[^0-9a-zA-Z]+/]).each(function(i,o){
        if(o.test(password)) strength++;
    });
    return strength;
}

/**
 * 显示密码的强度及其提示两个密码是否一致
 * @param password 用户输入密码
 */
function showStrength(password) {
    var strength = getPasswordStrength(password);
    var desc = strength < 3 ? '低' : (strength < 5 ? '中' : '高');
    $('#passwordStrength').html(desc);
    if ($("input[name='rePassword']").val() != null && "" != $("input[name='password']").val()){
        if (password != $("input[name='rePassword']").val()) {
            $('#rePasswordStrength').html('两次密码不一致！');
        }
    }
}

/**
 * 判断两次密码是否一致
 * @param rePassword 确认的密码
 */
function isRePassword(rePassword) {
    if (rePassword != $("input[name='password']").val()) {
        $('#rePasswordStrength').html('两次密码不一致！');
        return false;
    }
    $('#rePasswordStrength').html('OK');
    return true;
}

/**
 * 获取验证码
 */
function sendemail(){
    var obj = $("#getCode");
    if (!validataMobile() || validataMobileIsExist()){
        return;
    }
    $.ajax({
        type: "post",//请求类型
        url: "getCode.do",//请求的url
        data: {"mobile": $("input[name='mobile']").val()},
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {
            if (data == "ok") {
                layer.msg('验证码已发送到您的手机，请注意查收！');
                codeIsNull = true;
            }else {
                layer.msg('验证码发送错误，请稍后重试！');
                codeIsNull = false;
            }
        },
        error: function () {
            layer.msg("服务器连接异常！请联系系统管理员");
        }
    });
    settime(obj);
}