package com.example.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //  首页跳转
    @GetMapping("/")
    String toIndex() {
        return "index";
    }

    //  登录跳转
    @GetMapping("/login")
    String login() {
        return "login";
    }

    //  登录
    @PostMapping("/ajaxLogin")
    @ResponseBody
    String ajaxLogin(String username, String password) {
        try {
            //收集实体/凭据信息
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //  权限测试(403)
    @GetMapping("/permissionTest")
    @RequiresPermissions("companyUser:user:save")
    String test(RedirectAttributes redirectAttributes) {
        //重定向传递的数据
        redirectAttributes.addAttribute("a", "hello");
        redirectAttributes.addAttribute("b", "world");
        return "redirect:/redirectTest";
    }

    //  重定向测试
    @GetMapping("/redirectTest")
    @ResponseBody
    String test2(String a, String b) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return "账号:" + user.getUsername() + "密码:" + user.getPassword() + "接收重定向传递的数据:" + a + "," + b;
    }

}
