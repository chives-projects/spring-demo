package com.csc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    /**
     * try request http://localhost:8082/index
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index", "/", "/hello"})
    public String index(Model model) {
        model.addAttribute("title", "测试");
        model.addAttribute("atext", "这个冬天不太Cool");
        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }
    @RequestMapping(value = {"/grant"})
    public String grant(Model model) {
        return "grant";
    }
}
