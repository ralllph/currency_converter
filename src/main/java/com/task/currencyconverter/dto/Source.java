package com.task.currencyconverter.dto;

import com.task.currencyconverter.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Source {
    private Currency currency;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Source{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
