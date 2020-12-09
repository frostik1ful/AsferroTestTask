package com.example.demo.service.interfaces;


public interface NumbersApiService {
    String retrieveTextByNumber(int number);

    String retrieveTenMostPopularNumbers();

    String retrieveAverageLatency();

    String retrieveAverageSuccessRate();
}
