package defaultPackage.domain.dao;

import defaultPackage.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDao extends JpaRepository<Weather, Long> {
    Weather findByCity(String city);
}
