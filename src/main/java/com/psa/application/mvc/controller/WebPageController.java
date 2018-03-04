package com.psa.application.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class WebPageController {
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration() {
        return "redirect:/index.html";
    }
}
