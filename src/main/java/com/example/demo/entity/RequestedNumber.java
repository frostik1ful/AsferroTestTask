package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "number")
public class RequestedNumber {
    @Id
    private String id;
    private Integer number;
    private Long latency;
    private boolean isRequestSuccess;

    public RequestedNumber() {
    }

    public RequestedNumber(Integer number, Long latency, boolean isRequestSuccess) {
        this.number = number;
        this.latency = latency;
        this.isRequestSuccess = isRequestSuccess;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getLatency() {
        return latency;
    }

    public void setLatency(long latency) {
        this.latency = latency;
    }

    public boolean isRequestSuccess() {
        return isRequestSuccess;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        isRequestSuccess = requestSuccess;
    }
}
