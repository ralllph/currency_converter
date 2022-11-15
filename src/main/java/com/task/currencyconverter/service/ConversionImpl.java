package com.task.currencyconverter.service;

import com.task.currencyconverter.dto.RequestDto;
import com.task.currencyconverter.dto.Response;
import com.task.currencyconverter.enums.Currency;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@AllArgsConstructor
@Service
public class ConversionImpl  implements  ConversionInterface{

    //for api calls.  bean was created in application
    RestTemplate restTemplate;
    //environment allows you access variables in application properties e.g apikey
    Environment environment;


    @Override
    public Response convertToTargetCurrency(RequestDto requestDto) {
        BigDecimal targetAmount = requestDto.getTargetAmount();
        Currency targetCurrency = requestDto.getTargetCurrency();
        Currency sourceCurrency = requestDto.getSourceCurrency();
        String transRateUrl = environment.getProperty("transRateUrl");
        String url = transRateUrl+ targetAmount + "&destination_currency="+targetCurrency+"&source_currency="+ sourceCurrency;
        return  getExchangeRate(url,environment.getProperty("apiKey"));
    }

    @Override
    public Response getExchangeRate(String url, String apiKey) {
        //headers to pass in secret key
        HttpHeaders headers = new HttpHeaders();
        //JSON FORMAT
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer" +" "+ apiKey);
        //Http entity
        HttpEntity requestHeader = new HttpEntity(headers);
        //array of objects to contain a response. Object[].class means make it return an array of objects
        //Object[] exchangeResponse = restTemplate.getForObject(url, Object[].class);
        ResponseEntity<Response> response= restTemplate.exchange(url, HttpMethod.GET, requestHeader, Response.class);
        return response.getBody();
    }
}
