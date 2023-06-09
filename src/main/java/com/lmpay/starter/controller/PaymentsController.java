package com.lmpay.starter.controller;


import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.processor.AggregatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/lmpay/api/v1/payments/")
public class PaymentsController {
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 1000L;

    private final AggregatorFactory aggregatorFactory;

    public PaymentsController(AggregatorFactory aggregatorFactory) {
        this.aggregatorFactory = aggregatorFactory;
    }

    @PostMapping(value = "booktransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> bookTransaction(@RequestBody GenericRequest request) {
        return handleBankRequest(() -> aggregatorFactory.bookTransaction(request));
    }

    @PostMapping(value = "confirmtransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> confirmTransaction(@RequestBody GenericRequest request) {
        return handleBankRequest(() -> aggregatorFactory.confirmTransaction(request));
    }

    private ResponseEntity<Object> handleBankRequest(Callable<String> call) {
        int attempt = 1;
        while (true) {
            try {
                String response = call.call();
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                if (attempt >= MAX_RETRY_ATTEMPTS) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
                }
                attempt++;
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            }
        }
    }
}

