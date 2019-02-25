/**
 * 登陆界面js操作
 */
/**
 * 提示信息
 */
function validaLogin() {
    //用户名
    $('#name').focus(function () {
        $(this).val("");
    });
    $('#name').blur(function () {
        if ($("#name").val() == null || "" == ($("#name").val())) {
            layer.tips('用户名不能为空', '#name', {
                tips: [2, '#A4D3EE']
            });
        }
    });
    //密码
    $('#password').focus(function () {
        $(this).val("");
    });
    $('#password').blur(function () {
        if ($("#password").val() == null || "" == ($("#password").val())) {
            layer.tips('密码不能为空', '#password', {
                tips: [2, '#A4D3EE']
            });
        }
    });
    //验证码
    $('#tryCode').focus(function () {
        $(this).val("");
    });
    $('#tryCode').blur(function () {
        if ($("#tryCode").val() == null || "" == ($("#tryCode").val())) {
            layer.tips('验证码不能为空', '#tryCode', {
                tips: [2, '#A4D3EE']
            });
        }
    });
}

/**
 * 验证输入
 * @returns {boolean}
 */
function validaInputIsNull() {
    if ($("#name").val() == null || $("#name").val() == ""){
        layer.tips('用户名不能为空', '#name', {
            tips: [2, '#A4D3EE']
        });
        return false;
    } else if ($('#password').val() == null || $('#password').val() == "") {
        layer.tips('密码不能为空', '#password', {
            tips: [2, '#A4D3EE']
        });
        return false;
    }else if ($("#tryCode").val() == null || "" == $("#tryCode").val()){
        layer.tips('验证码不能为空', '#tryCode', {
            tips: [2, '#A4D3EE']
        });
        return false;
    }
    return true;
}

$(function () {
    validaLogin();
    //登陆
    $("#btoOk").click(function () {
        if (!validaInputIsNull()) {
            return;
        }
        $("#btoOk").attr("disable", "false");
        $.ajax({
            type: "post",//请求类型
            url: "userLogin.do",//请求的url
            data: {"loginCode": $("#name").val(), "password": $("#password").val(),"tryCode":$("#tryCode").val()},
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {
                if (data == "ok") {
                    location.href = "main.html";
                }else if (data == "code"){
                    $("#imgCode").attr('src','/kaptcha?d=\'+new Date()*1');
                    $("#tryCode").val("");
                    layer.msg('验证码错误，请重新输入！');
                } else {
                    layer.msg('账号或者密码有误，请重试！');
                }
            },
            error: function () {
                layer.msg("服务器连接异常！请联系系统管理员");
            }
        });
    });
})