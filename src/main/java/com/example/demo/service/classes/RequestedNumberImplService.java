package com.example.demo.service.classes;

import com.example.demo.entity.RequestedNumber;
import com.example.demo.repository.RequestedNumberRepository;
import com.example.demo.respond.AverageSuccessRateRespond;
import com.example.demo.respond.MostPopularNumbersRespond;
import com.example.demo.service.interfaces.RequestedNumberDAO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;

import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestedNumberImplService implements RequestedNumberDAO {
    private final RequestedNumberRepository repository;
    private final RestHighLevelClient elasticsearchClient;
    @Autowired
    public RequestedNumberImplService(RequestedNumberRepository repository,RestHighLevelClient elasticsearchClient)
    {
        this.repository = repository;
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public void save(RequestedNumber number) {
        repository.save(number);
    }

    @Override
    public MostPopularNumbersRespond findTenMostPopular() {
        List<Long> topNumbers = new ArrayList<>();

        AggregationBuilder builder = AggregationBuilders
                .terms("agg").field("number")
                .subAggregation(AggregationBuilders.topHits("top")).size(10);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("number");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(builder);
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
            Terms agg = searchResponse.getAggregations().get("agg");
            for (Terms.Bucket entry : agg.getBuckets()){
                Long key = (Long) entry.getKey();
                topNumbers.add(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MostPopularNumbersRespond(topNumbers);
    }

    @Override
    public Double findAverageLatency() {
        AvgAggregationBuilder builder = AggregationBuilders.avg("agg").field("latency");

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("number");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(builder);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
            Avg avg = searchResponse.getAggregations().get("agg");
            return avg.getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0d;
    }

    @Override
    public AverageSuccessRateRespond findAverageSuccessRate() {
        Optional<Double> optional = repository.countAllByIsRequestSuccessIsTrue();
        return new AverageSuccessRateRespond(optional.map(aDouble -> aDouble / repository.count() * 100d).orElse(0d));
    }

}
