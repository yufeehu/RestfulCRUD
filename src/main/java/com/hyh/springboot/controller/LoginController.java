package com.hyh.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功，防止表单重复提交，可以重定向带主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        //登录失败
        map.put("msg","用户名或密码错误");
        return "login";
    }
    @RequestMapping("/user/logout")
    public String logout( HttpSession session){
        //使session失效
        session.invalidate();
        return "redirect:main.html";
    }
}
