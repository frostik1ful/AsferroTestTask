package com.example.demo.service.classes;

import com.example.demo.factory.NumberFactory;
import com.example.demo.respond.AverageLatencyRespond;
import com.example.demo.respond.AverageSuccessRateRespond;
import com.example.demo.respond.MostPopularNumbersRespond;
import com.example.demo.respond.TextByNumberRespond;
import com.example.demo.service.interfaces.NumbersApiService;
import com.example.demo.service.interfaces.RequestedNumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumbersApiImplService implements NumbersApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final RequestedNumberDAO requestedNumberDAO;

    @Autowired
    public NumbersApiImplService(RequestedNumberDAO requestedNumberDAO) {
        this.requestedNumberDAO = requestedNumberDAO;
    }

    @Override
    public TextByNumberRespond retrieveTextByNumber(int number) {
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
        return new TextByNumberRespond(answer);
    }

    @Override
    public MostPopularNumbersRespond retrieveTenMostPopularNumbers() {
        return requestedNumberDAO.findTenMostPopular();
    }

    @Override
    public AverageLatencyRespond retrieveAverageLatency() {
        Double latency = requestedNumberDAO.findAverageLatency();
        return new AverageLatencyRespond(latency);
    }

    @Override
    public AverageSuccessRateRespond retrieveAverageSuccessRate() {
        return requestedNumberDAO.findAverageSuccessRate();

    }

}
