package com.app.controllers;

import com.app.dao.CountryDao;
import com.app.dao.ShopDao;
import com.app.model.Country;
import com.app.model.Shop;
import com.app.model.dto.ShopDto;
import com.app.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {

    private CountryDao countryDao;
    private ShopService shopService;
    private ShopDao shopDao;

    public ShopController(CountryDao countryDao, ShopService shopService, ShopDao shopDao) {
        this.countryDao = countryDao;
        this.shopService = shopService;
        this.shopDao = shopDao;
    }

    @RequestMapping("/shop/add")
    public String shopAddPost(@ModelAttribute ShopDto shopDto, BindingResult bindingResult, HttpServletRequest request, Model model)
    {
        shopService.add(shopDto);
        return "redirect:/";
    }

    @GetMapping("/shop/add")
    public String shopAddGet(Model model)
    {
        model.addAttribute("shop", new ShopDto());
        model.addAttribute("country", countryDao.findAll());
        return "shop/add";
    }

    @GetMapping
    public String shopSelectAll(Model model)
    {

        System.out.println("all shops");
        System.out.println(shopDao.findAll().toString());
        return "shop/select_all";
    }

}
