package com.ahmedukamel.hms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("logged", SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null);
        return "index";
    }
}
