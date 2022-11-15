package com.task.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String status;
    private String  message;
    private Data data;

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
