package defaultPackage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String city;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    public Weather(String city, double humidity, double pressure, double temp, double temp_max, double temp_min) {
        this.city = city;
        this.humidity = humidity;
        this.pressure= pressure;
        this.temp= temp;
        this.temp_max = temp_max;
        this.temp_min  = temp_min;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public String getCity() {
        return city;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}
