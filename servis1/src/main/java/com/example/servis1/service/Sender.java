package com.example.servis1.service;

import com.example.servis1.dao.UserDao;
import com.example.servis1.domain.User;
import com.example.servis1.domain.Weather;
import com.example.servis1.domain.dto.MsgDto;
import com.example.servis1.domain.dto.WeatherDto;
import com.example.servis1.service.feign.RemoteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OutputChannel.class)
@RequiredArgsConstructor
public class Sender {
    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    private final OutputChannel outputChannel;
    @Autowired
    private final UserDao userDao;

    @Autowired
    private final RemoteService remoteService;

    @Scheduled(fixedDelay = 100000)
    public void publish() {
        //Mora da ide kroz sve korisnike i da salje za sve njihove gradove poruku
        for(User u: userDao.findAll()){
            String msg = "";
            logger.info(u.toString());
            if(u.getCities().size()==0) continue;
            for(Weather w: u.getCities()){
                //Za svaki grad salji zahtev remoteServicu za prognozu i onda to salji
                String city = w.getCity();
                WeatherDto weatherDto = remoteService.fetchWeather(city);
                msg = msg.concat(weatherDto.getCity() +": " + " Temp: " + weatherDto.getMain().getTemp() + " Humidity: " + weatherDto.getMain().getHumidity() +
                        " Pressure: " + weatherDto.getMain().getPressure() + " Max temp: "+ weatherDto.getMain().getTemp_max() + " Min temp: " + weatherDto.getMain().getTemp_min() + "\n");
            }
            MsgDto msgDto = new MsgDto(u.getEmail(), "Weather", msg);
            outputChannel.output().send(MessageBuilder.withPayload(msgDto).build());
            logger.info(msg);
        }

    }
}
