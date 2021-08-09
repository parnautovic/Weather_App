package defaultPackage.ctrl;

import defaultPackage.domain.Weather;
import defaultPackage.domain.dao.WeatherDao;
import defaultPackage.domain.dto.WeatherDto;
import defaultPackage.service.WeatherService;
import defaultPackage.service.feign.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateCtrl{
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherDao weatherDao;
    @Autowired
    private RemoteService remoteService;

    @Scheduled(fixedRate = 1000000)
    private void info(){
        //Update baze
        //Za svaki grad koji imamo u njoj
        //Updateuj info
        WeatherDto weatherDto;
        for(Weather w: weatherDao.findAll()){
            //Dohvati info za APIja
            weatherDto = remoteService.fetchByCity(w.getCity());

            Weather newW = new Weather(weatherDto.getCity(),
                    weatherDto.getMain().getHumidity(),
                    weatherDto.getMain().getPressure(),
                    weatherDto.getMain().getTemp(),
                    weatherDto.getMain().getTemp_max(),
                    weatherDto.getMain().getTemp_min()
            );
            newW.setId(w.getId());
            weatherDao.save(newW);
        }
    }

}
