package com.example.servis1.service.feign;

import com.example.servis1.domain.dto.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "remoteService", url = "http://localhost:8082")
public interface RemoteService {

    @PostMapping("/addCity?city={city}")
    void addCity(@PathVariable("city") String city);

    @GetMapping("/weather?city={city}")
    WeatherDto fetchWeather(@PathVariable("city") String city);
}
