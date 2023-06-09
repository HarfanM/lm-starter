package com.lmpay.starter.processor;

import com.lmpay.starter.config.AppProperties;
import com.lmpay.starter.model.GenericRequest;
import io.github.jamsesso.jsonlogic.JsonLogicException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class AggregatorFactory {
    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    public AggregatorFactory(AppProperties appProperties, RestTemplate restTemplate) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
    }

    public String bookTransaction(GenericRequest request) throws IOException {
        BankProcessor routerService = new BankProcessor(appProperties, restTemplate);
        return routerService.getProcess(request).bookTransaction(request);
    }

    public String confirmTransaction(GenericRequest request) throws JsonLogicException, IOException {
        BankProcessor routerService = new BankProcessor(appProperties, restTemplate);
        return routerService.getProcess(request).confirmTransaction(request);
    }
}

