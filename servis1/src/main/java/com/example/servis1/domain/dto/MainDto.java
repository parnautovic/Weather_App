package com.example.servis1.domain.dto;

import lombok.Data;

@Data
public class MainDto {
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    public MainDto(double humidity, double pressure, double temp, double temp_max, double temp_min) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp= temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }


    public double getTemp(){
        return this.temp;
    }

    public double getPressure(){
        return this.pressure;
    }

    public double getHumidity(){
        return this.humidity;
    }

    public double getTemp_min(){
        return this.temp_min;
    }

    public double getTemp_max(){
        return this.temp_max;
    }
}
