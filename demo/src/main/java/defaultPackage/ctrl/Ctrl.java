package defaultPackage.ctrl;

import defaultPackage.domain.dto.WeatherDto;
import defaultPackage.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Ctrl {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDto weather(String city){
        return weatherService.fetchWeather(city);
    }

    @PostMapping("/addCity")
    public void addString(String city){
        logger.info(city);
        weatherService.addCity(city);
    }
}
