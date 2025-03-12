package com.example.clima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/home")
    public String userHome(Authentication authentication, Model model) {
        model.addAttribute("nomeUsuario", authentication.getName());
        return "user/home";
    }
}
