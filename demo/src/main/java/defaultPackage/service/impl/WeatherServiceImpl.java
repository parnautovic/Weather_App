package defaultPackage.service.impl;

import defaultPackage.domain.Weather;
import defaultPackage.domain.dao.WeatherDao;
import defaultPackage.domain.dto.MainDto;
import defaultPackage.domain.dto.WeatherDto;
import defaultPackage.service.WeatherService;
import defaultPackage.service.feign.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RemoteService remoteService;
    @Autowired
    private WeatherDao weatherDao;

    @Override
    public WeatherDto fetchWeather(String city) {
        //Ovde ubacivanje u bazu
        //Kada se trazi konkretan grad ako ga nema u bazi
        //Dodaj ga
        Weather existing = weatherDao.findByCity(city);
        WeatherDto weatherDto;
        if(existing==null){
            //Nema ga u bazi
            weatherDto = remoteService.fetchByCity(city);
            Weather w = new Weather(weatherDto.getCity(),
                    weatherDto.getMain().getHumidity(),
                    weatherDto.getMain().getPressure(),
                    weatherDto.getMain().getTemp(),
                    weatherDto.getMain().getTemp_max(),
                    weatherDto.getMain().getTemp_min()
                    );
            weatherDao.save(w);
        }else{
            MainDto mainDto = new MainDto(
                    existing.getHumidity(),
                    existing.getPressure(),
                    existing.getTemp(),
                    existing.getTemp_max(),
                    existing.getTemp_min());

            weatherDto = new WeatherDto(existing.getCity(), mainDto);
        }

        return weatherDto;
    }

    @Override
    public void addCity(String city) {
        Weather existing = weatherDao.findByCity(city);
        WeatherDto weatherDto;
        if(existing==null) {
            //Nema ga u bazi
            weatherDto = remoteService.fetchByCity(city);
            Weather w = new Weather(weatherDto.getCity(),
                    weatherDto.getMain().getHumidity(),
                    weatherDto.getMain().getPressure(),
                    weatherDto.getMain().getTemp(),
                    weatherDto.getMain().getTemp_max(),
                    weatherDto.getMain().getTemp_min()
            );
            weatherDao.save(w);
        }
    }
}
