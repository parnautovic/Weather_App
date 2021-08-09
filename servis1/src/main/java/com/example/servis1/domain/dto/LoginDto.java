package com.example.servis1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class LoginDto {

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;
}
