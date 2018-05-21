package com.app.controllers;


import com.app.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("category")
public class CategoryController {

    @PostMapping("/category/add")
    public String categoryAddPost(@ModelAttribute Category category, BindingResult bindingResult, HttpServletRequest request, Model model)
    {
        return "category/add";
    }

    @GetMapping("/category/add")
    public String categoryAddGet(Model model)
    {
        model.addAttribute("category", new Category());
        return "category/add";
    }
}
