package com.example.demo.service.interfaces;


import com.example.demo.respond.AverageLatencyRespond;
import com.example.demo.respond.AverageSuccessRateRespond;
import com.example.demo.respond.MostPopularNumbersRespond;
import com.example.demo.respond.TextByNumberRespond;

public interface NumbersApiService {
    TextByNumberRespond retrieveTextByNumber(int number);

    MostPopularNumbersRespond retrieveTenMostPopularNumbers();

    AverageLatencyRespond retrieveAverageLatency();

    AverageSuccessRateRespond retrieveAverageSuccessRate();
}
