package com.app.controllers;

import com.app.dao.*;
import com.app.model.Category;
import com.app.model.Customer;
import com.app.model.EGuarantee;
import com.app.model.Product;
import com.app.model.dto.CustomerDto;
import com.app.model.dto.ProductDto;
import com.app.service.CustomerSerivce;
import com.app.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("product")
public class ProductController {

    private CategoryDao categoryDao;
    private ProductService productService;
    private ProducerDao producerDao;
    private ProductDao productDao;
    private CustomerDao customerDao;
    private CountryDao countryDao;
    private CustomerSerivce customerSerivce;

    public ProductController(CategoryDao categoryDao, ProductService productService, ProducerDao producerDao, ProductDao productDao, CustomerDao customerDao, CountryDao countryDao, CustomerSerivce customerSerivce) {
        this.categoryDao = categoryDao;
        this.productService = productService;
        this.producerDao = producerDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.countryDao = countryDao;
        this.customerSerivce = customerSerivce;
    }

    @RequestMapping("/product/add")
    public String productAddPost(@ModelAttribute ProductDto product, BindingResult bindingResult, HttpServletRequest request, Model model)
    {
        productService.add(product);
        return "redirect:/";
    }

    @GetMapping("/product/add")
    public String productAddGet(Model model)
    {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("category", categoryDao.findAll());
        model.addAttribute("producer", producerDao.findAll());
        model.addAttribute("guarantee", EGuarantee.values());

        return "product/add";
    }


    @GetMapping("product/select_all")
    public String productSelectAll(Model model)
    {
        model.addAttribute("product", productDao.findAll());
        return "product/select_all";
    }

    @GetMapping("/product/details/{id}")
    public String productDetails(@PathVariable Long id, Model model)
    {
        model.addAttribute("product", producerDao.findOneById(id));
        return "product/details";
    }

    @GetMapping("product/delete/{id}")
    public String productDelete(@PathVariable Long id)
    {
        Product product = productDao.findOneById(id).get();
        producerDao.delete(product.getId());
        return "redirect:/product/select_all";
    }

    @GetMapping("product/modify/{id}")
    public String personModifyGet(@PathVariable Long id, Model model)
    {
        model.addAttribute("category", categoryDao.findAll());
        model.addAttribute("producer", producerDao.findAll());
        model.addAttribute("guarantee", EGuarantee.values());
        model.addAttribute("product", productService.getById(id));
        return "product/modify";
    }

    @PostMapping("product/modify")
    public String productModifyPost(@ModelAttribute Product product, Model model)
    {
        productDao.update(product);
        return "redirect:/product/select_all";
    }


}
