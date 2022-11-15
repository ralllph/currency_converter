package com.task.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    private int rate;
    private Source source;
    private Destination destination;

    @Override
    public String toString() {
        return "Data{" +
                "rate=" + rate +
                ", source=" + source +
                ", destination=" + destination +
                '}';
    }
}
