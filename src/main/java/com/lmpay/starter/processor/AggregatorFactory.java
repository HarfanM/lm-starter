package com.lmpay.starter.processor;

import com.lmpay.starter.config.AppProperties;
import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.repository.PartnerTransactionRepository;
import io.github.jamsesso.jsonlogic.JsonLogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class AggregatorFactory {
    private final AppProperties appProperties;
    private final RestTemplate restTemplate;
    private final PartnerTransactionRepository partnerTransactionRepository;


    @Autowired
    public AggregatorFactory(AppProperties appProperties,
                             RestTemplate restTemplate,
                             PartnerTransactionRepository partnerTransactionRepository) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
        this.partnerTransactionRepository = partnerTransactionRepository;
    }

    public String bookTransaction(GenericRequest request) throws IOException, JsonLogicException {
        BankProcessor routerService = new BankProcessor(appProperties, restTemplate, partnerTransactionRepository);
        return routerService.getProcess(request).bookTransaction(request);
    }

    public String confirmTransaction(GenericRequest request) throws JsonLogicException, IOException {
        BankProcessor routerService = new BankProcessor(appProperties, restTemplate, partnerTransactionRepository);
        return routerService.getProcess(request).confirmTransaction(request);
    }
}

