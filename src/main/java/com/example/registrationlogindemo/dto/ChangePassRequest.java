package com.example.registrationlogindemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangePassRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
