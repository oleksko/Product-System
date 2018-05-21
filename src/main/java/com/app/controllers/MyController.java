package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("welcome")
public class MyController {

    @RequestMapping("/")
    public String test()
    {
        return "welcome";
    }

}
