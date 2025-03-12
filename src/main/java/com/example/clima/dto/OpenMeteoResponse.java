package com.example.clima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenMeteoResponse {

    private Double latitude;
    private Double longitude;

    @JsonProperty("current")
    private CurrentWeather current;

    @Data
    public static class CurrentWeather {
        private String time;

        @JsonProperty("temperature_2m")
        private Double temperature;

        private Double precipitation;

        @JsonProperty("weather_code")
        private Integer weatherCode;

        @JsonProperty("wind_speed_10m")
        private Double windSpeed;


        public String getWeatherDescription() {
            return switch (weatherCode) {
                case 0 -> "Céu limpo";
                case 1, 2, 3 -> "Parcialmente nublado";
                case 45, 48 -> "Nevoeiro";
                case 51, 53, 55 -> "Chuvisco";
                case 61, 63, 65 -> "Chuva";
                case 71, 73, 75 -> "Neve";
                case 80, 81, 82 -> "Chuva forte";
                case 95, 96, 99 -> "Tempestade";
                default -> "Desconhecido";
            };
        }
    }
}