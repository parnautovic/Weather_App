package com.example.servis1.service.impl;

import com.example.servis1.dao.UserDao;
import com.example.servis1.dao.WeatherDao;
import com.example.servis1.domain.User;
import com.example.servis1.domain.Weather;
import com.example.servis1.domain.dto.LoginDto;
import com.example.servis1.domain.dto.RegisterDto;
import com.example.servis1.domain.dto.UserDtoResp;
import com.example.servis1.service.feign.RemoteService;
import com.example.servis1.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    @Autowired
    private final UserDao userDao;

    @Autowired
    private final WeatherDao weatherDao;

    @Autowired
    private final RemoteService remoteService;

    @Override
    public List<UserDtoResp> getAllUsers(String token) {
        if(token == null || token.length()==0) return null;
        Optional<User> optUser = userDao.findByEmail( getEmailByToken(token));
        if(!optUser.isPresent()) return null;

        List<UserDtoResp> list = new ArrayList<>();
        for(User u: userDao.findAll()){
            list.add(new UserDtoResp(u.getEmail()));
        }
        return list;
    }

    @Override
    public List<String> getUserCities(String token, String email) {
        if(token == null || token.length()==0) return null;
        Optional<User> optUser = userDao.findByEmail( getEmailByToken(token));
        if(!optUser.isPresent()) return null;

        List<String> list = new ArrayList<>();

        Optional<User> optUser2 = userDao.findByEmail(email);
        if(optUser2.isPresent()){
            User u  = optUser2.get();
            for(Weather w: u.getCities()){
                list.add(w.getCity());
            }
            return list;
        }else{
            //Ako korisnik ne postoji
            return null;
        }
    }

    @Override
    public String register(RegisterDto registerDto) {

        Optional<User> optUser = userDao.findByEmail(registerDto.getEmail());
        if(optUser.isPresent()){
            //Postoji taj korisnik vrati neku gresku
            return "Taj korisnik vec postoji";
        }else{
            //Kreiraj novog i ubaci u bazu
            User u  = new User();
            u.setEmail(registerDto.getEmail());
            u.setPassword(registerDto.getPassword());
            u.setName(registerDto.getName());
            userDao.save(u);
            //posalji ga na login

            return login(
                    new LoginDto(u.getEmail(), u.getPassword()));
        }
    }

    @Override
    public String login(LoginDto loginDto) {
        //Nadje korisnika u spisku i vrati mu token koji on koristi
        Optional<User> optUser = userDao.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(optUser.isPresent()){
            //Postoji ovaj korisnik
            return getTokenByEmail(loginDto.getEmail());
        }else{
            //Ako korisnik ne postoji
            return "Pogresni kredencijali!";
        }
    }

    @Override
    public String addCityToMyList(String city, String token) {

        if(city== null || city.length()==0)
            return "Naziv grada ne postoji";
        if(token == null || token.length()==0)
            return "Niste ulogovani!";

        Optional<User> optUser = userDao.findByEmail( getEmailByToken(token));

        if(optUser.isPresent()){
            User user = optUser.get();

            Weather w = weatherDao.findByCity(city);
            if(w==null){
                remoteService.addCity(city);
                Weather wNew = weatherDao.findByCity(city);
                user.getCities().add(wNew);
                wNew.getUsers().add(user);
                userDao.save(user);
                return "Uspesno dodat grad!";
            }
            if(!user.getCities().contains(w)){
                user.getCities().add(w);
                w.getUsers().add(user);

                userDao.save(user);
                return "Uspesno dodat grad!";

            }else return "Vec ste pretplaceni na taj grad!";

        }else return "Niste uloguvani!";
    }

    private static final String KEY = "a4B4SjAKwXCP9H37a4B4SjAKwXCP9H37a4B4SjAKwXCP9H37";

    @Override
    public String getTokenByEmail(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    @Override
    public String getEmailByToken(String token) {
        return Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
