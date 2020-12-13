package com.example.demo.repository;

import com.example.demo.entity.RequestedNumber;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestedNumberRepository extends ElasticsearchRepository<RequestedNumber, String> {
    Optional<Double> countAllByIsRequestSuccessIsTrue();

}
