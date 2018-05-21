package com.app.controllers;

import com.app.model.exceptions.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionsController {
    @ExceptionHandler({MyException.class})
    public String myExceptionMethod(Model model, MyException e) {
        model.addAttribute("errorMessage", e.getErrorMessage());
        model.addAttribute("errorDate", e.getDateTime());
        return "errorPage";
    }
}
