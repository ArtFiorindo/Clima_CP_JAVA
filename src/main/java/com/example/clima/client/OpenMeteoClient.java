package com.example.clima.client;

import com.example.clima.dto.OpenMeteoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenMeteoClient {

    private final RestClient restClient;
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    public OpenMeteoClient() {
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public OpenMeteoResponse getWeatherData(Double latitude, Double longitude) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current", "temperature_2m,precipitation,weather_code,wind_speed_10m")
                        .build())
                .retrieve()
                .body(OpenMeteoResponse.class);
    }
}
