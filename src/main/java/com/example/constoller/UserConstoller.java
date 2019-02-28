package com.example.constoller;

import com.alibaba.fastjson.JSON;
import com.example.entity.Auuser;
import com.example.entity.Chatrecord;
import com.example.service.ChatrecordService;
import com.example.service.UserService;
import com.example.util.DataMaps;
import com.example.threadUtil.CustomThreadPoolExecutor;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

//@RestController//@RestController注解能够使项目支持Rest
@Controller
public class UserConstoller {
    /**
     * 图片验证码
     */
    final String KAPTCHA_CODE = "code";
    /**
     * 短信验证码
     */
    final String MOBILE_CODE = "mobileCode";
    @Autowired
    UserService userService;

    @Autowired
    ChatrecordService chatrecordService;
    /**
     * 注入验证码服务
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/{view}.html")
    public String view(@PathVariable String view) {
        return view;
    }

    @RequestMapping("/{view}")
    public String getView(@PathVariable String view) {
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
        if (!storeCode.toUpperCase().equals(tryCode.toUpperCase())) {
            return JSON.toJSONString("code");
        } else if (user == null) {
            return JSON.toJSONString("no");
        }
        //创建线程池
        ThreadPoolExecutor executor = CustomThreadPoolExecutor.getCustomThreadPoolExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (userService.modifyLastLogin(user.getLoginCode()) > 0){
                    System.out.println("ok");
                }
            }
        });
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
        if (userService.validataName(name) == 0){
            return JSON.toJSONString("no");
        }
        return JSON.toJSONString("ok");
    }


    @RequestMapping("/validataMobile.do")
    @ResponseBody
    private String validataMobile(String mobile) {
        if (userService.validataMobile(mobile) == 0){
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
        }else if (userService.insertUser(auuser) == 0){
            return JSON.toJSONString("no");
        }
        auuser.setUserName(auuser.getLoginCode());//默认件登录名赋给显示名
        request.getSession().setAttribute(DataMaps.LOGIN_USER, auuser);
        return JSON.toJSONString("ok");
    }

    @RequestMapping("/index2.html")
    public String requestIndex2(Model model,HttpServletRequest request) {
        Auuser user = (Auuser) request.getSession().getAttribute(DataMaps.LOGIN_USER);
        List<Auuser> auusers = userService.getAuusers(user.getId());
        List<Chatrecord> chatrecords =  chatrecordService.getChatrecoresById(user.getId());
        model.addAttribute("auusers", auusers);
        model.addAttribute("chatrecords", chatrecords);
        return "index2";
    }

}
