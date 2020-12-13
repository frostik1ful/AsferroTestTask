package com.example.demo.respond;

import java.util.List;

public class MostPopularNumbersRespond extends Respond {
    private final List<Long> numbers;

    public MostPopularNumbersRespond(List<Long> numbers) {
        super("10 most popular numbers");
        this.numbers = numbers;
    }

    public List<Long> getNumbers() {
        return numbers;
    }
}
