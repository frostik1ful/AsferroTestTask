package com.example.demo.service.interfaces;

import com.example.demo.entity.RequestedNumber;
import com.example.demo.respond.AverageSuccessRateRespond;
import com.example.demo.respond.MostPopularNumbersRespond;


public interface RequestedNumberDAO {
    void save(RequestedNumber number);

    MostPopularNumbersRespond findTenMostPopular();

    Double findAverageLatency();

    AverageSuccessRateRespond findAverageSuccessRate();
}
