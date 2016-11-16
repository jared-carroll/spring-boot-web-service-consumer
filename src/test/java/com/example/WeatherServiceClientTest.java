package com.example;

import com.cdyne.ws.weatherws.GetCityForecastByZIPResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ws.test.client.RequestMatchers.connectionTo;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WeatherServiceClientTest {
    @Autowired
    WeatherServiceClient weatherServiceClient;

    MockWebServiceServer server;

    @Before
    public void setUp() {
        server = MockWebServiceServer.createServer(weatherServiceClient);
    }

    @Test
    public void getCityForecastByZIP() {
        Source requestPayload = new StringSource("" +
                "<GetCityForecastByZIP xmlns=\"http://ws.cdyne.com/WeatherWS/\">\n" +
                "    <ZIP>60622</ZIP>\n" +
                "</GetCityForecastByZIP>"
        );
        Source responsePayload = new StringSource("" +
                "<GetCityForecastByZIPResponse xmlns=\"http://ws.cdyne.com/WeatherWS/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <GetCityForecastByZIPResult>\n" +
                "        <Success>true</Success>\n" +
                "        <ResponseText>string</ResponseText>\n" +
                "        <State>string</State>\n" +
                "        <City>string</City>\n" +
                "        <WeatherStationCity>string</WeatherStationCity>\n" +
                "        <ForecastResult>\n" +
                "            <Forecast>\n" +
                "                <Date>dateTime</Date>\n" +
                "                <WeatherID>short</WeatherID>\n" +
                "                <Desciption>string</Desciption>\n" +
                "                <Temperatures xsi:nil=\"true\" />\n" +
                "                <ProbabilityOfPrecipiation xsi:nil=\"true\" />\n" +
                "            </Forecast>\n" +
                "            <Forecast>\n" +
                "                <Date>dateTime</Date>\n" +
                "                <WeatherID>short</WeatherID>\n" +
                "                <Desciption>string</Desciption>\n" +
                "                <Temperatures xsi:nil=\"true\" />\n" +
                "                <ProbabilityOfPrecipiation xsi:nil=\"true\" />\n" +
                "            </Forecast>\n" +
                "        </ForecastResult>\n" +
                "    </GetCityForecastByZIPResult>\n" +
                "</GetCityForecastByZIPResponse>"
        );
        server.expect(connectionTo("http://wsf.cdyne.com/WeatherWS/Weather.asmx"))
                .andExpect(payload(requestPayload))
                .andRespond(withPayload(responsePayload));

        GetCityForecastByZIPResponse getCityForecastByZIPResponse = weatherServiceClient.getCityForecastByZIP("60622");

        assertThat(getCityForecastByZIPResponse.getGetCityForecastByZIPResult().isSuccess()).isEqualTo(true);
    }
}