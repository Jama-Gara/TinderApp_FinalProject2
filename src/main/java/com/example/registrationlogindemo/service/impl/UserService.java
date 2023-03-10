package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    void saveUserDetails(UserDto userDto,String email);
    void changePassword(UserDto userDto,String email);
}
