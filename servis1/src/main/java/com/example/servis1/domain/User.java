package com.example.servis1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ManyToMany( fetch = FetchType.EAGER , mappedBy = "users", cascade = CascadeType.ALL)
    private List<Weather> cities = new ArrayList<>();

    public List<Weather> getCities() {
        return cities;
    }

    public String toString(){
        return cities.size() + "";
    }
}
