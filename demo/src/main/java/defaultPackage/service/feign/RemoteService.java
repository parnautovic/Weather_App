package defaultPackage.service.feign;

import defaultPackage.domain.dto.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weatherClient", url = "http://api.openweathermap.org/data/2.5")
public interface RemoteService {

    @GetMapping("/weather?q={city}&units=metric&APPID=6ec3e10599834991f2611a69b3e5db8f")
    WeatherDto fetchByCity(@PathVariable("city") String city);
}
