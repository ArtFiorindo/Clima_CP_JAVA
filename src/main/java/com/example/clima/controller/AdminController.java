package com.example.clima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/cidades/home")
    public String adminHome(Authentication authentication, Model model) {

        model.addAttribute("nomeUsuario", authentication.getName());
        return "admin/home";
    }
}

