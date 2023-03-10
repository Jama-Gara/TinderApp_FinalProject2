package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    private final UserRepository userRepository;
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
        public Set<User> findAll() {return userRepository.findAll().stream().collect(Collectors.toSet());}
        public Optional<User> findById(Long id) {return userRepository.findById(id);}

    }


