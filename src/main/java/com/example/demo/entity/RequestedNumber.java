package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "requested_number", schema = "public")
public class RequestedNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
