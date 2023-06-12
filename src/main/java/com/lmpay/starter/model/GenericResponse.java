package com.lmpay.starter.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    @JsonProperty("LMReceiveagentName")
    private String LMReceiveagentName;

    @JsonProperty("LMReceiveagentServiceName")
    private String LMReceiveagentServiceName;

    @JsonProperty("LMReceiveagentCode")
    private String LMReceiveagentCode;

    @JsonProperty("LMSendPrincipleAmount")
    private String LMSendPrincipleAmount;

    @JsonProperty("LMSendFee")
    private String LMSendFee;

    @JsonProperty("LMSendpartnerFee")
    private String LMSendpartnerFee;

    @JsonProperty("LMSendReceivePartnerFee")
    private String LMSendReceivePartnerFee;

    @JsonProperty("LMSendCharge")
    private String LMSendCharge;

    @JsonProperty("LMSendTax")
    private String LMSendTax;

    @JsonProperty("LMSendTotalCharges")
    private String LMSendTotalCharges;

    @JsonProperty("LMSendDiscount")
    private String LMSendDiscount;

    @JsonProperty("LMSendNetAmount")
    private String LMSendNetAmount;

    @JsonProperty("LMSendSettlementAmount")
    private String LMSendSettlementAmount;

    @JsonProperty("LMReceiveAmount")
    private String LMReceiveAmount;

    @JsonProperty("LMReceiveFee")
    private String LMReceiveFee;

    @JsonProperty("LMReceiveTax")
    private String LMReceiveTax;

    @JsonProperty("LMNetReceiveAmount")
    private String LMNetReceiveAmount;

    @JsonProperty("LMExchangeRate")
    private String LMExchangeRate;

    @JsonProperty("LMIndicativeExchangeRate")
    private String LMIndicativeExchangeRate;

    @JsonProperty("LMIndicativeCurrency")
    private String LMIndicativeCurrency;

    @JsonProperty("LMSendCurrency")
    private String LMSendCurrency;

    @JsonProperty("LMReceiveCurrency")
    private String LMReceiveCurrency;

    @JsonProperty("LMSendSettlementCurrency")
    private String LMSendSettlementCurrency;

    @JsonProperty("LMReceiveSettlementCurrency")
    private String LMReceiveSettlementCurrency;

    @JsonProperty("LMSendSettlementAmountToLM")
    private String LMSendSettlementAmountToLM;

    @JsonProperty("LMTotalSettlementAmountToLM")
    private String LMTotalSettlementAmountToLM;

    @JsonProperty("LMReceiveSettlementAmountFromLM")
    private String LMReceiveSettlementAmountFromLM;

    @JsonProperty("LMTotalReceiveSettlementAmountFromLM")
    private String LMTotalReceiveSettlementAmountFromLM;

    @JsonProperty("LMTransactionReferenceNo")
    private String LMTransactionReferenceNo;

    @JsonProperty("LMPartnerCode")
    private String LMPartnerCode;

    @JsonProperty("LMPayoutPartnerName")
    private String LMPayoutPartnerName;

    @JsonProperty("LMResponseMessage")
    private String LMResponseMessage;

    @JsonProperty("LMResponseCode")
    private int LMResponseCode;

    @JsonProperty("LMPayinPartnerRef")
    private String LMPayinPartnerRef;

    @JsonProperty("LMTransactionDate")
    private String LMTransactionDate;
}
