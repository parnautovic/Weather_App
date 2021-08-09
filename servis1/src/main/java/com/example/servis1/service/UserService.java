package com.example.servis1.service;

import com.example.servis1.domain.dto.LoginDto;
import com.example.servis1.domain.dto.RegisterDto;
import com.example.servis1.domain.dto.UserDtoResp;

import java.util.List;

public interface UserService {

    List<UserDtoResp> getAllUsers(String token);

    List<String> getUserCities(String token, String email);


    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    String addCityToMyList(String city, String token);

    String getTokenByEmail(String email);

    String getEmailByToken(String token);

}
