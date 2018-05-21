package com.app.controllers;


import com.app.dao.CountryDao;
import com.app.dao.CustomerDao;
import com.app.model.dto.CustomerDto;
import com.app.service.CustomerSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("customer")
public class CustomerController {

    private CustomerSerivce customerSerivce;
    private CountryDao countryDao;
    private CustomerDao customerDao;

    public CustomerController(CustomerSerivce customerSerivce, CountryDao countryDao, CustomerDao customerDao) {
        this.customerSerivce = customerSerivce;
        this.countryDao = countryDao;
        this.customerDao = customerDao;
    }

    @PostMapping("/customer/add")
    public String customerAddPost(@ModelAttribute CustomerDto customer, BindingResult bindingResult, HttpServletRequest request, Model model)
    {
        customerSerivce.add(customer);
        return "redirect:/customer/add";
    }

    @GetMapping("/customer/add")
    public String customerAddGet(Model model)
    {
        model.addAttribute("customer", new CustomerDto());
        model.addAttribute("country", countryDao.findAll());
        return "customer/add";
    }

    @GetMapping("/customer/select_all")
    public String customerSelectAll(Model model)
    {
        model.addAttribute("customer", customerDao.findAll());
        System.out.println(customerDao.findAll().toString());
        return "customer/select_all";
    }
    @GetMapping("customer/delete/{id}")
    public String customerDelete(@PathVariable Long id)
    {
        customerSerivce.remove(id);
        return "redirect:/customer/select_all";
    }
}
