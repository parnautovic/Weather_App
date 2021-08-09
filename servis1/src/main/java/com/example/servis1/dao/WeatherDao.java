package com.example.servis1.dao;

import com.example.servis1.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDao extends JpaRepository<Weather, Long> {

    Weather findByCity(String city);
}
