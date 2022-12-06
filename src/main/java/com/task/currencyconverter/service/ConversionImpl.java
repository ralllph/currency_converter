package com.task.currencyconverter.service;

import com.task.currencyconverter.dto.RequestDto;
import com.task.currencyconverter.dto.Response;
import com.task.currencyconverter.enums.Currency;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;


@AllArgsConstructor
@Service
public class ConversionImpl  implements  ConversionInterface{

    //for api calls.  bean was created in main springboot application
    RestTemplate restTemplate;
    //environment allows you access variables in application properties e.g apikey
    Environment environment;
    //another option for ret template is web client
    //bean was created in the main springboot application
    WebClient.Builder webClient;



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

        //using web client instead of rest template

         Response response2 = webClient.build()
                //we are doing a get
                .get()
                //the url
                .uri(url)
                .header("Authorization", "Bearer" +" "+ apiKey)
                 // json type
                 .accept(MediaType.APPLICATION_JSON)
                //get the response
                .retrieve()
                //Mono is a promise yet to be resolved
                 //Flux for collection of objects
                .bodyToMono(Response.class)
                //block means hold on till you get the response asin promise is resolved
                .block();


        return response2;
        //return response.getBody();
    }
}
