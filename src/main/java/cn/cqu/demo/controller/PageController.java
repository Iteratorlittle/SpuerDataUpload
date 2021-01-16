package cn.cqu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(path = "/")
    public String mainpage(){
        return "index";
    }
}