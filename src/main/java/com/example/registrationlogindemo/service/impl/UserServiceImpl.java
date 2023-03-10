package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.enums.Gender;
import com.example.registrationlogindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname()); //+ " " + userDto.getLastName()
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
    @Override
    public void changePassword(UserDto userDto, String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
        }
    }

    @Override
    public void saveUserDetails(UserDto userDto,String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String firstName = userDto.getFirstname();
            String surName = userDto.getSurname();
            String nickName = userDto.getNickname();
            String location = userDto.getLocation();
            String gender = userDto.getGender();
            String dateOfBirth = userDto.getBirthday();
            String userInfo = userDto.getUserInfo();
            if (firstName != null && !firstName.isEmpty()) {
                user.setFirstname(firstName);
            }
            if (surName != null && !surName.isEmpty()) {
                user.setSurname(surName);
            }
            if (nickName != null && !nickName.isEmpty()) {
                user.setNickname(nickName);
            }
            if (location != null && !location.isEmpty()) {
                user.setLocation(location);
            }
            if (gender != null && !gender.isEmpty()) {
                user.setGender(Gender.valueOf(gender));
            }
            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                user.setBirthday(LocalDate.parse(dateOfBirth));
            }
            if (userInfo != null && !userInfo.isEmpty()) {
                user.setUserInfo(userInfo);
            }
            System.out.println(user.getNickname() + " " + user.getFirstname() + " " + user.getSurname());
            userRepository.save(user);
        }
    }
}