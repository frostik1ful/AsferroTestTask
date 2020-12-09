package com.example.demo.repository;

import com.example.demo.entity.RequestedNumber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestedNumberRepository extends CrudRepository<RequestedNumber, Long> {
    @Query(nativeQuery = true, value = "SELECT rn.number FROM requested_number rn GROUP BY rn.number  ORDER BY COUNT(rn.id) DESC LIMIT 10 ")
    List<Optional<String>> findTopTen();

    @Query("SELECT AVG(rn.latency)  FROM RequestedNumber rn")
    Optional<Long> findAverageLatency();

    Optional<Double> countAllByIsRequestSuccessIsTrue();

}
