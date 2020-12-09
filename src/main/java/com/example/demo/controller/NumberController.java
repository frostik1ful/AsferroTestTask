package com.example.demo.controller;

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
    public String textAboutNumber(@RequestParam int number) {
        return numbersApiService.retrieveTextByNumber(number);
    }

    @GetMapping("/tenMostPopularNumbers")
    public String tenMostPopularNumbers() {
        return numbersApiService.retrieveTenMostPopularNumbers();
    }

    @GetMapping("/averageLatency")
    public String averageLatency() {
        return numbersApiService.retrieveAverageLatency();
    }

    @GetMapping("/averageSuccessRate")
    public String averageSuccessRate() {
        return numbersApiService.retrieveAverageSuccessRate();
    }
}
