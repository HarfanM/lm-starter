package com.lmpay.starter.processor;



import com.lmpay.starter.config.AppProperties;
import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.service.BankService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankProcessor {
    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    public BankProcessor(AppProperties appProperties, RestTemplate restTemplate) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
    }

    public BankService getProcess(GenericRequest request) {
        if ("INR".equals(request.getLMSendCurrency())) {
            return null;
        }
        else {
            return new HelloPaisaProcessor(appProperties, restTemplate);
        }
    }
}

