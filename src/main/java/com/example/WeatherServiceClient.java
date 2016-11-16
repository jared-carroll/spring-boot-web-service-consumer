package com.example;

import com.cdyne.ws.weatherws.GetCityForecastByZIP;
import com.cdyne.ws.weatherws.GetCityForecastByZIPResponse;
import com.cdyne.ws.weatherws.ObjectFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class WeatherServiceClient extends WebServiceGatewaySupport {
    public GetCityForecastByZIPResponse getCityForecastByZIP(String zip) {
        ObjectFactory objectFactory = new ObjectFactory();
        GetCityForecastByZIP getCityForecastByZIP = objectFactory.createGetCityForecastByZIP();
        getCityForecastByZIP.setZIP(zip);
        return (GetCityForecastByZIPResponse) getWebServiceTemplate()
                .marshalSendAndReceive(getCityForecastByZIP, new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));
    }
}
