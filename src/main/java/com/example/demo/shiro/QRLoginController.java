package com.example.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class QRLoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //获取扫码Token
    @GetMapping("/refreshCode")
    @ResponseBody
    String refreshCode(HttpServletRequest request) {
        String qrToken = UUID.randomUUID().toString().replace("-", "");
        request.getSession().setAttribute("qrToken","0");
        return qrToken;
    }

    //校验是否登录
    @GetMapping("/loginJudge")
    @ResponseBody
    String loginJudge(HttpServletRequest request,String qrToken) {
        String suc = (String) request.getSession().getAttribute(qrToken);
        if ("1".equals(suc)) {
            return "scan_success";
        }
        if ("2".equals(suc)) {
            return "login_success";
        }
        return "error";
    }

    //登录
    @GetMapping("/loginQr")
    String loginQr(String username, String password,String qrToken,HttpServletRequest request) {
        System.out.println("qrToken"+qrToken);
        request.getSession().removeAttribute(qrToken);
        //shiro登录
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "index";
    }

    //测试app扫码成功
    @GetMapping("/apploginQr")
    @ResponseBody
    String apploginQr(HttpServletRequest request,String token) {
        request.getSession().setAttribute(token,"1");
        return "success";
    }

    //测试app登录成功
    @GetMapping("/apploginQr2")
    @ResponseBody
    String apploginQr2(HttpServletRequest request,String token) {
        request.getSession().setAttribute(token,"2");
        return "success";
    }
}
