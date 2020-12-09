package com.example.demo.service.classes;

import com.example.demo.entity.RequestedNumber;
import com.example.demo.repository.RequestedNumberRepository;
import com.example.demo.service.interfaces.RequestedNumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestedNumberImplService implements RequestedNumberDAO {
    RequestedNumberRepository repository;

    @Autowired
    public RequestedNumberImplService(RequestedNumberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(RequestedNumber number) {
        repository.save(number);
    }

    @Override
    public List<Optional<String>> findTenMostPopular() {
        return repository.findTopTen();
    }

    @Override
    public Optional<Long> findAverageLatency() {
        return repository.findAverageLatency();
    }

    @Override
    public Double findAverageSuccessRate() {
        Optional<Double> optional = repository.countAllByIsRequestSuccessIsTrue();
        return optional.map(aDouble -> aDouble / repository.count() * 100d).orElse(0d);
    }
}
