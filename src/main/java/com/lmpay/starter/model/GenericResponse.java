package com.lmpay.starter.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private String LMReceiveagentName;
    private String LMReceiveagentServiceName;
    private String LMReceiveagentCode;
    private String LMSendPrincipleAmount;
    private String LMSendFee;
    private String LMSendpartnerFee;
    private String LMSendReceivePartnerFee;
    private String LMSendCharge;
    private String LMSendTax;
    private String LMSendTotalCharges;
    private String LMSendDiscount;
    private String LMSendNetAmount;
    private String LMSendSettlementAmount;
    private String LMReceiveAmount;
    private String LMReceiveFee;
    private String LMReceiveTax;
    private String LMNetReceiveAmount;
    private String LMExchangeRate;
    private String LMIndicativeExchangeRate;
    private String LMIndicativeCurrency;
    private String LMSendCurrency;
    private String LMReceiveCurrency;
    private String LMSendSettlementCurrency;
    private String LMReceiveSettlementCurrency;
    private String LMSendSettlementAmountToLM;
    private String LMTotalSettlementAmountToLM;
    private String LMReceiveSettlementAmountFromLM;
    private String LMTotalReceiveSettlementAmountFromLM;
    private String LMPayoutPartnerRef;
    private String LMPartnerCode;
    private String LMPayoutPartnerName;
    private String LMResponseMessage;
    private int LMResponseCode;
    private String LMPayinPartnerRef;
}
