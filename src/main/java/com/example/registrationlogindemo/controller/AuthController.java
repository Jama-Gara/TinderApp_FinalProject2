package com.example.registrationlogindemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@Controller
public class AuthController {
    @RequestMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }
}