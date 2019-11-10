package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    private String currentUserId;

     // 用户登录跳转到login界面
    @RequestMapping(value = "/index", method = RequestMethod.GET) // 获取用户输入数据
    public String hello(Model model){
        return "index";
    }

    // 获取用户输入数据
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String login(HelloUser user, Model model, HttpSession session) {
        String userId = user.getId();
        String password = user.getPassword();
        logger.info("login  your id and password is :" + userId + " " + password);
        HelloUser one = helloService.getOne(userId);    // 判断对象是否为空
        if (one.getId() == null) {
            model.addAttribute("loginInfo", "该用户不存在!");
            return "index";
        } else if (one.getPassword().equals(password)) {
            logger.info("you've logged!!");
            model.addAttribute("loginInfo", "登录成功！");
            // 展示当前用户信息
            currentUserId = new String(userId);

            return "redirect:welcome";
        } else {
            model.addAttribute("loginInfo", "密码验证失败！(重新输入)");
            return "index";
        }
    }

    // 给用户提供注册界面
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String pageRegister() {
        return "register";
    }

    // 获取用户输入数据
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String finishRegister(HelloUser user, Model model) {
        String userId = user.getId();
        String username = user.getName();
        String password = user.getPassword();
        logger.info("register id and password is :" + userId + " " + password);
        Map<String, String> para = new HashMap<String, String>();
        HelloUser one = helloService.getOne(userId);    // 判断对象是否为空
        if (one.getId() != null) {
            model.addAttribute("registerInfo", "该用户已存在！");
            return "register";
        } else {
            //新注册用户需要给出用户名
            para.put("id", userId);
            para.put("name", username);
            para.put("password", password);
            helloService.InsertUser(para);
            model.addAttribute("registerInfo", "注册成功!");

            currentUserId = new String(userId);

            return "redirect:index"; //重新登录
        }
    }


    // 用户登录跳转到欢迎界面
    @RequestMapping(value = "/welcome", method = RequestMethod.GET) // 获取用户输入数据
    public void welcome(HelloUser user, Model model) {
        logger.info("欢迎进入个人界面");

        HelloUser result = helloService.getOne(currentUserId);
        assert (result.getId() != null);    // 当前用户肯定在数据库中
        model.addAttribute("welcome_name", result.getName());
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.GET) // 修改用户信息界面
    public String showModifyPage(HelloUser user, Model model) {
        logger.info("modify user info!!!!!");

        HelloUser one = helloService.getOne(currentUserId); // 当前用户ID
        model.addAttribute("welcome_id", one.getId());
        model.addAttribute("welcome_name", one.getName());
        model.addAttribute("welcome_password", one.getPassword());
        return "modifyUserInfo";
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST) // 修改用户信息
    public String ModifyUserInfo(HelloUser user, Model model) {
        Map<String, String> para = new HashMap<String, String>();
        para.put("id", user.getId());
        para.put("name", user.getName());
        para.put("password", user.getPassword());
        helloService.UpdateByID(para);
        return "redirect:modifyUserInfo";
    }


    @RequestMapping(value = "/deleteUser") // 删除用户
    public String deleteUser(HelloUser user, Model model) {
        logger.info("delete!!!!");
        logger.info("delete user" + currentUserId);
        if(currentUserId != null) {
            helloService.DeleteByID(currentUserId);
        }
        currentUserId = null;
        return "redirect:index";
    }
}
