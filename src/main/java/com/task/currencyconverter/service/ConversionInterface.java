package com.task.currencyconverter.service;

import com.task.currencyconverter.dto.RequestDto;
import com.task.currencyconverter.dto.Response;

public interface ConversionInterface {
    Response convertToTargetCurrency(RequestDto requestDto);

    //callApi
    Response getExchangeRate(String url , String apiKey);
}
