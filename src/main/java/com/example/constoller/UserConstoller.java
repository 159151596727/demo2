package com.example.constoller;

import com.alibaba.fastjson.JSON;
import com.example.entity.Auuser;
import com.example.service.UserService;
import com.example.util.DataMaps;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

//@RestController//@RestController注解能够使项目支持Rest
@Controller
//@RequestMapping(value = "/user")
public class UserConstoller {
    final String KAPTCHA_CODE = "code";//图片验证码
    final String MOBILE_CODE = "mobileCode";//短信验证码
    @Autowired
    UserService userService;//注入用户服务
    @Autowired
    DefaultKaptcha defaultKaptcha;//注入验证码服务

    @RequestMapping("/{view}.html")//method = RequestMethod.GET
    public String view(String view) {
        return view;
    }

    @RequestMapping("/{view}")
    public String getView(String view) {
        return view;
    }

    /**
     * 获取验证码
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        request.getSession().setAttribute(KAPTCHA_CODE, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    /**
     * 用户登录
     *
     * @param request
     * @param loginCode 用户名
     * @param password  密码
     * @param tryCode   验证码
     * @return
     */
    @RequestMapping("/userLogin.do")
    @ResponseBody
    private String userLogin(HttpServletRequest request, String loginCode, String password, String tryCode) {
        String storeCode = (String) request.getSession().getAttribute(KAPTCHA_CODE);
        Auuser user = userService.userLogin(loginCode, password);
        if (!storeCode.toUpperCase().equals(tryCode.toUpperCase())) {//全大写匹配
            return JSON.toJSONString("code");
        } else if (user == null) {
            return JSON.toJSONString("no");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (userService.modifyLastLogin(user.getLoginCode())){
                    System.out.println("ok");
                }
            }
        }).start();
        //保存登录用户到session
        request.getSession().setAttribute(DataMaps.LOGIN_USER, user);
        return JSON.toJSONString("ok");
    }

    /**
     * 获取验证码
     * @param request
     * @param mobile 手机号
     * @return
     */
    @RequestMapping("/getCode.do")
    @ResponseBody
    private String getCode(HttpServletRequest request, String mobile) {
        //这边获取验证码，因没开通短信服务所以这里留空。
        request.getSession().setAttribute(MOBILE_CODE, "8888");
        return JSON.toJSONString("ok");
    }

    /**
     * 验证用户名是否已存在
     * @param name
     * @return
     */
    @RequestMapping("/validataName.do")
    @ResponseBody
    private String validataName(String name) {
        if (userService.validataName(name)){
            return JSON.toJSONString("no");
        }
        return JSON.toJSONString("ok");
    }


    @RequestMapping("/validataMobile.do")
    @ResponseBody
    private String validataMobile(String mobile) {
        if (userService.validataMobile(mobile)){
            return JSON.toJSONString("no");
        }
        return JSON.toJSONString("ok");
    }

    /**
     * 注册用户
     * @param request
     * @param auuser
     * @return
     */
    @RequestMapping("/register.do")
    @ResponseBody
    private String register(HttpServletRequest request,Auuser auuser) {
        if (auuser == null){
            return JSON.toJSONString("null");
        }
        String code = (String) request.getSession().getAttribute(MOBILE_CODE);
        if (!auuser.getCode().equals(code)){
            return JSON.toJSONString("code");
        }else if (!userService.insertUser(auuser)){
            return JSON.toJSONString("no");
        }
        auuser.setUserName(auuser.getLoginCode());//默认件登录名赋给显示名
        request.getSession().setAttribute(DataMaps.LOGIN_USER, auuser);
        return JSON.toJSONString("ok");
    }

}
