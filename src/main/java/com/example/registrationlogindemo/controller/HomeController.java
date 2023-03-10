package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    @GetMapping("user")
    public String showLoginForm() {
        return "user";
    }
    @PostMapping("/user")
    public String submitDate(@RequestParam("date") String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate selectedDate = LocalDate.parse(dateStr, formatter);
        return "redirect:/user";
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/mainpage";
        } else {
            return "redirect:/login";
        }
    }
}
