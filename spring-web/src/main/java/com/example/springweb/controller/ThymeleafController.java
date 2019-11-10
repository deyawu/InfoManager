package com.example.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello(Model model) {
        //model.addAttribute("greeting", "Hello!");
        return "test";
    }
}
