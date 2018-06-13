package com.app.controllers;


import com.app.dao.CountryDao;
import com.app.dao.CustomerDao;
import com.app.model.Customer;
import com.app.model.dto.ConverterDto;
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
    private ConverterDto converterDto;


    public CustomerController(CustomerSerivce customerSerivce, CountryDao countryDao, CustomerDao customerDao, ConverterDto converterDto) {
        this.customerSerivce = customerSerivce;
        this.countryDao = countryDao;
        this.customerDao = customerDao;
        this.converterDto = converterDto;
    }

    @PostMapping("/customer/add")
    public String customerAddPost(@ModelAttribute CustomerDto customer, BindingResult bindingResult, HttpServletRequest request, Model model)
    {
        System.out.println("customer controller : ");
        CustomerDto customerDto = customer;
        System.out.println(customerDto.toString());
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

    @GetMapping("customer/modify/{id}")
    public String customerModifyGet(@PathVariable Long id, Model model)
    {
        model.addAttribute("customer", customerDao.findOneById(id));
        model.addAttribute("country", countryDao.findAll());
        return "customer/modify";
    }

    @PostMapping("customer/modify")
    public String customerModifyPost(@ModelAttribute Customer customer, Model model)
    {
        customerDao.update(customer);
        return "redirect:/customer/select_all";
    }

}
