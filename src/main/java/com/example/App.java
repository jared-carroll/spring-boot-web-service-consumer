package com.example;

import com.cdyne.ws.weatherws.ForecastReturn;
import com.cdyne.ws.weatherws.GetCityForecastByZIPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Bean
    @Profile("!test")
    public ApplicationRunner applicationRunner() {
        return args -> {
            GetCityForecastByZIPResponse getCityForecastByZIPResponse = weatherServiceClient.getCityForecastByZIP("60622");
            ForecastReturn getCityForecastByZIPResult = getCityForecastByZIPResponse.getGetCityForecastByZIPResult();
            System.out.println("getCityForecastByZIPResult.isSuccess() = " + getCityForecastByZIPResult.isSuccess());
        };
    }
}
