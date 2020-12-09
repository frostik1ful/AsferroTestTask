package com.example.demo.service.classes;

import com.example.demo.factory.NumberFactory;
import com.example.demo.service.interfaces.NumbersApiService;
import com.example.demo.service.interfaces.RequestedNumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NumbersApiImplService implements NumbersApiService {
    private StringBuilder builder;

    private final RestTemplate restTemplate = new RestTemplate();

    private final RequestedNumberDAO requestedNumberDAO;

    @Autowired
    public NumbersApiImplService(RequestedNumberDAO requestedNumberDAO) {
        this.requestedNumberDAO = requestedNumberDAO;
    }

    @Override
    public String retrieveTextByNumber(int number) {
        String answer = "";
        String apiUrl = "http://numbersapi.com/";
        try {
            long timeStart = System.currentTimeMillis();
            answer = restTemplate.getForObject(apiUrl + number, String.class);
            long latency = System.currentTimeMillis() - timeStart;
            requestedNumberDAO.save(NumberFactory.createNumber(number, latency, true));
        } catch (Exception e) {
            requestedNumberDAO.save(NumberFactory.createEmptyNumber());
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String retrieveTenMostPopularNumbers() {
        builder = new StringBuilder();
        requestedNumberDAO.findTenMostPopular().forEach(s -> s.ifPresent(value -> builder.append(value).append(", ")));
        return "10 most popular numbers - " + builder.toString();
    }

    @Override
    public String retrieveAverageLatency() {
        return requestedNumberDAO.findAverageLatency().map(aLong -> "AverageLatency is " + aLong + "ms").orElse("AverageLatency is " + 0L + "ms");
    }

    @Override
    public String retrieveAverageSuccessRate() {
        return "Average success rate " + requestedNumberDAO.findAverageSuccessRate() + "%";
    }

}
