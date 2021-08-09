package defaultPackage.domain.dto;

import lombok.Data;

@Data
public class WeatherDto {

    private String name;
    private MainDto main;

    public WeatherDto(String city, MainDto mainDto) {
        this.name = city;
        this.main = mainDto;
    }

    public String getCity(){
        return this.name;
    }
    public MainDto getMain(){
        return this.main;
    }
}
