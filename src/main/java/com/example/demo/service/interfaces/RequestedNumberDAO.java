package com.example.demo.service.interfaces;

import com.example.demo.entity.RequestedNumber;

import java.util.List;
import java.util.Optional;

public interface RequestedNumberDAO {
    void save(RequestedNumber number);

    List<Optional<String>> findTenMostPopular();

    Optional<Long> findAverageLatency();

    Double findAverageSuccessRate();
}
