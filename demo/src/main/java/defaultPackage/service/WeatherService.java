package defaultPackage.service;

import defaultPackage.domain.dto.WeatherDto;

public interface WeatherService {

     WeatherDto fetchWeather(String city);

     void addCity(String city);
}
