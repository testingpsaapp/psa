package com.psa.application.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebPageController {
	
	@GetMapping("/")
    public String greeting(ModelMap modelMap) {
        //System.out.println("hii::"+name);
        modelMap.addAttribute("name", "abhi");
        return "index";
    }
}
