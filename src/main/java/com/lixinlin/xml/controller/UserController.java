package com.lixinlin.xml.controller;

import com.lixinlin.xml.bean.User;
import com.lixinlin.xml.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private userService  userService;
    @RequestMapping("/add")
    public String add(User user, Model model, @RequestParam(value = "hobby",required = false) String [] hobby,
                      @RequestParam("file") MultipartFile file) throws IOException {
        String upload = userService.upload(file);
        user.setImgpath(upload);
        //获取爱好
        user.setHobby(hobby);
        String sex = user.getSex();
        if(sex.equals("on")){
            user.setSex("男");
        }else{
            user.setSex("女");
        }
        String name = user.getName();
        User user1 = userService.getUserByName(name);
        if(user1==null){
//          //说明用户名没有重复，可以注册
            userService.add(user);
            model.addAttribute("msg1","注册成功 请登录");
            model.addAttribute("user",user);
            return "login";
        }else{
            //说明用户名已存在
            model.addAttribute("msg","用户名已存在,请重新注册");
            return "register";
        }
    }
    @PostMapping({"/login"})
    public String login(@RequestParam("name") String name, @RequestParam("password") String pwd, HttpSession session, Model model){
        Object msg1 = model.getAttribute("msg1");
        model.addAttribute("msg1",msg1);
        User user1 = userService.getUserByName(name);

        if(user1==null){
//            model.addAttribute("msg","用户名不存在");
            model.addAttribute("msg","账号或密码错误");

            return "login";
        }
        if(name.equals(user1.getName())&&pwd.equals(user1.getPassword())){
            session.setAttribute("loginuser",user1);
            System.out.println("登录成功");
            //登录成功重定向到main页面，重定向防止表单重复提交
            return "redirect:/index.html";
        }
        else{
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }
    }

    @RequestMapping({"/","/index.html"})
    public String index(HttpSession session,Model model){
//        是否登录拦截器，过滤器
        Object msg = session.getAttribute("loginuser");
        if(msg!=null){
            return "index";
        }else{
            model.addAttribute("msg","请先登录");
            return "login";
        }

    }
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/register.html")
    public String register(){
        return "register";
    }


}
