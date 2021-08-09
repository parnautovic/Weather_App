package com.example.servis1.ctrl;

import com.example.servis1.domain.dto.LoginDto;
import com.example.servis1.domain.dto.RegisterDto;
import com.example.servis1.domain.dto.UserDtoResp;
import com.example.servis1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserCtrl {
//Login postojeceg i register

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    @PostMapping("/register")
    public String register(@Validated @RequestBody RegisterDto registerDto){
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @PostMapping("/addCity")
    public String addCityToMyList(String city, String token){
        return userService.addCityToMyList(city, token);
    }

    @GetMapping("/allUsers")
    public List<UserDtoResp> getAllUsers(String token){
        return userService.getAllUsers( token);
    }


    @GetMapping("/userCities")
    public List<String> getUserCities(String token, String email){
        return userService.getUserCities(token, email);
    }

}
