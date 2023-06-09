package com.lmpay.starter.service;

import com.lmpay.starter.model.GenericRequest;
import io.github.jamsesso.jsonlogic.JsonLogicException;

import java.io.IOException;

public interface BankService {
    String confirmTransaction(GenericRequest request) throws JsonLogicException, IOException;
    String bookTransaction(GenericRequest request) throws IOException;
//    String balanceEnquiry(GenericRequest request);
//    String transactionEnquiry(GenericRequest request);
//    String upiValAddress(GenericRequest request);
//    String upiReqPay(GenericRequest request);
//    String beneficiaryNameEnquiry(GenericRequest request);
//    String agentBanks();
//    String agentRates();
}

