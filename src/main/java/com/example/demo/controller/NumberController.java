package com.example.demo.controller;

import com.example.demo.respond.*;
import com.example.demo.service.interfaces.NumbersApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class NumberController {
    private final NumbersApiService numbersApiService;

    @Autowired
    public NumberController(NumbersApiService numbersApiService) {
        this.numbersApiService = numbersApiService;
    }

    @GetMapping("/textAboutNumber")
    public Respond textAboutNumber(@RequestParam String number) {
        System.out.println(number);
        try {
            int value = Integer.parseInt(number);
            return numbersApiService.retrieveTextByNumber(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new WrongInputRespond();
        }
    }


    @GetMapping("/tenMostPopularNumbers")
    public Respond tenMostPopularNumbers() {
        return numbersApiService.retrieveTenMostPopularNumbers();
    }

    @GetMapping("/averageLatency")
    public Respond averageLatency() {
        return numbersApiService.retrieveAverageLatency();
    }

    @GetMapping("/averageSuccessRate")
    public Respond averageSuccessRate() {
        return numbersApiService.retrieveAverageSuccessRate();
    }
}
