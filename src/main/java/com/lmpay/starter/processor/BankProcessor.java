package com.lmpay.starter.processor;



import com.lmpay.starter.config.AppProperties;
import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.repository.PartnerTransactionRepository;
import com.lmpay.starter.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankProcessor {
    private final AppProperties appProperties;
    private final RestTemplate restTemplate;
    private final PartnerTransactionRepository partnerTransactionRepository;

    @Autowired
    public BankProcessor(AppProperties appProperties,
                         RestTemplate restTemplate,
                         PartnerTransactionRepository partnerTransactionRepository) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
        this.partnerTransactionRepository = partnerTransactionRepository;
    }

    public BankService getProcess(GenericRequest request) {
        if ("INR".equals(request.getLMReceiveCurrency())) {
            return new FederalBankProcessor(appProperties, restTemplate, partnerTransactionRepository);
        }
        else {
            return new HelloPaisaProcessor(appProperties, restTemplate, partnerTransactionRepository);
        }
    }
}

