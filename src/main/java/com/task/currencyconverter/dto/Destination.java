package com.task.currencyconverter.dto;

import com.task.currencyconverter.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Destination {
    private Currency currency;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Destination{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
