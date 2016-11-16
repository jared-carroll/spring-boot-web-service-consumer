package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WeatherServiceConfig {
    @Bean
    public WeatherServiceClient weatherServiceClient() {
        WeatherServiceClient weatherServiceClient = new WeatherServiceClient();
        weatherServiceClient.setDefaultUri("http://wsf.cdyne.com/WeatherWS/Weather.asmx");
        weatherServiceClient.setMarshaller(jaxb2Marshaller());
        weatherServiceClient.setUnmarshaller(jaxb2Marshaller());
        return weatherServiceClient;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.cdyne.ws.weatherws");
        return jaxb2Marshaller;
    }
}
