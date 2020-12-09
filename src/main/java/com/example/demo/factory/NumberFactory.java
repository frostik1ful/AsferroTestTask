package com.example.demo.factory;

import com.example.demo.entity.RequestedNumber;

public class NumberFactory {
    public static RequestedNumber createNumber(int number, long latency, boolean isRequestSuccess) {
        return new RequestedNumber(number, latency, isRequestSuccess);
    }

    public static RequestedNumber createEmptyNumber() {
        return new RequestedNumber(null, null, false);
    }
}
