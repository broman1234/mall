package com.mman.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @author 莫曼
 * @version 1.0
 */

@Controller
public class RedirectController {

    @GetMapping("/{url}")
    public ModelAndView redirect(@PathVariable("url") String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        modelAndView.addObject("cartList", new ArrayList<>());
        return modelAndView;
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/productCategory/main";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
