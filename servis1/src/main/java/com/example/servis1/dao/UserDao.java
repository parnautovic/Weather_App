package com.example.servis1.dao;

import com.example.servis1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    //Nadji usera sa email-om i password-om
    Optional<User> findByEmailAndPassword (String email, String password);

    Optional<User> findByEmail (String email);

}
