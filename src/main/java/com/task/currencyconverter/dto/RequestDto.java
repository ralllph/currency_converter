package com.task.currencyconverter.dto;

import com.task.currencyconverter.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestDto {

    private Currency sourceCurrency;


    private Currency targetCurrency;


    private BigDecimal targetAmount;
}
