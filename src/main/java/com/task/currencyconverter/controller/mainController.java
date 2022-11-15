package com.task.currencyconverter.controller;

import com.task.currencyconverter.dto.RequestDto;
import com.task.currencyconverter.dto.Response;
import com.task.currencyconverter.service.ConversionImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
public class mainController {
    ConversionImpl convert;
    @PostMapping("/convertCurrency")
    public ResponseEntity<Response> getCurrencyConverted(@RequestBody RequestDto requestDto){
        return new ResponseEntity<>(convert.convertToTargetCurrency(requestDto), HttpStatus.OK);
    }
}
