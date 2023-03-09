package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.Message;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.MessageRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.FavoritesService;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class MessageController {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private UserService userService;
    private FavoritesRepository favoritesRepository;

    @Autowired
    private FavoritesService favoritesService;
    public MessageController(UserRepository userRepository, MessageRepository messageRepository, UserService userService, FavoritesRepository favoritesRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.favoritesRepository = favoritesRepository;
    }

    @GetMapping("/chat")
    public String showMessageForm(Model model, HttpSession session) {
//
        Long currentUserId = (Long) session.getAttribute("userId");
        User sender = userService.findById(currentUserId).get();
        System.out.println(sender.getEmail());

        Set<User> likedUsers = favoritesService.findByStatusAndLikedBy("like", currentUserId).stream()
                .map(f -> f.getLikedUser())
                .collect(Collectors.toSet());

//        User likedUsers = model.addAttribute("likedUsers", likedUsers);
        String receiver = userService.findByEmail(likedUsers.stream().findFirst().get().getEmail()).getEmail();
        System.out.println("test receiver id");


//        receiverId = reciver.getId();

        if (sender != null) {
            model.addAttribute("sender", sender);
//            model.addAttribute("receiver", reciver);
            model.addAttribute("receiver",receiver);
            System.out.println(receiver);
            String username = (String) session.getAttribute("username"); // retrieve the username from the session
            model.addAttribute("username", username);
//            System.out.println(receiver);
//            System.out.println(username);
//
//            String userId = (String) session.getAttribute("userId"); // retrieve the userId from the session
//            model.addAttribute("userId", userId);
//
//            System.out.println(userId);

            return "chat";
        }
        return "chat";
    }



    @PostMapping("/chat")
    public String sendMessage(@RequestParam("receiverId") Long receiverId,
                              @RequestParam("msg_text") String msgText,
                              Principal principal) {
        User sender = userRepository.findByEmail(principal.getName());
        Favorites receiver = favoritesRepository.findById(receiverId).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMsg_text(msgText);
        System.out.println(message);
        messageRepository.save(message);

        return "redirect:/chat?receiverId=" + receiverId;
    }
}
