package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.enums.Gender;
import com.example.registrationlogindemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/forgot-password")
    public String showForgetPass() {
        return "forgot-password";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView submitLoginForm(SignInRequest request, HttpSession session) {
        String email = request.getEmail();
        String password = request.getPassword();
        log.info("lof in");
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(),userService.findByEmail(email).getPassword());
        log.info(userService.findByEmail(email).getEmail());
        log.info(password);
        log.info(userService.findByEmail(email).getPassword());
        if (isAuthenticated) {
            User byEmail = userService.findByEmail(email);
            Long id = byEmail.getId();
            String username = byEmail.getFirstname();
            String firstname = byEmail.getFirstname();
            String sesEmail = byEmail.getEmail();
            String surname = byEmail.getSurname();
            String nickname = byEmail.getNickname();
            String location = byEmail.getLocation();
            Gender gender = byEmail.getGender();
            String userInfo = byEmail.getUserInfo();
            log.info("giriş doğruysa, kullanıcıyı bir sonraki sayfaya yönlendirin");
            session.setAttribute("userId", id);
            session.setAttribute("username", username); // store the username in the session
            session.setAttribute("firstname", firstname);
            session.setAttribute("sesEmail", sesEmail);
            session.setAttribute("surname", surname);
            session.setAttribute("nickname", nickname);
            session.setAttribute("location", location);
            session.setAttribute("gender", gender);
            session.setAttribute("userInfo", userInfo);
            System.out.println(userInfo);
            return new RedirectView("/mainpage");

        } else {
            log.info("Error");
            return new RedirectView("login");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println("logOut page");
        if (session != null) {
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                session.removeAttribute(attributeName);
            }
            session.invalidate();
            return  "redirect:/login";
        }
        return "redirect:/login";
    }

}
