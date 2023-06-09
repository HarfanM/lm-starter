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
public class GenericRequest {
    @JsonProperty("LMSenderFirstName")
    private String LMSenderFirstName;
    @JsonProperty("LMSenderLastName")
    private String LMSenderLastName;
    @JsonProperty("LMSenderAddress")
    private String LMSenderAddress;
    @JsonProperty("LMSenderCity")
    private String LMSenderCity;
    @JsonProperty("LMSenderState")
    private String LMSenderState;
    @JsonProperty("LMSenderPostalCode")
    private String LMSenderPostalCode;
    @JsonProperty("LMSenderNationality")
    private String LMSenderNationality;
    @JsonProperty("LMSenderIDType")
    private String LMSenderIDType;
    @JsonProperty("LMSenderIDNumber")
    private String LMSenderIDNumber;
    @JsonProperty("LMSenderAddressCountry")
    private String LMSenderAddressCountry;
    @JsonProperty("LMSenderDOB")
    private String LMSenderDOB;
    @JsonProperty("LMSenderMobileISDPrefix")
    private String LMSenderMobileISDPrefix;
    @JsonProperty("LMSenderMobileNumber")
    private String LMSenderMobileNumber;
    @JsonProperty("LMDestinationCountry")
    private String LMDestinationCountry;
    @JsonProperty("LMReceiverFirstName")
    private String LMReceiverFirstName;
    @JsonProperty("LMReceiverLastName")
    private String LMReceiverLastName;
    @JsonProperty("LMReceiverAddress")
    private String LMReceiverAddress;
    @JsonProperty("LMReceiverCity")
    private String LMReceiverCity;
    @JsonProperty("LMReceiverMobileISDPrefix")
    private String LMReceiverMobileISDPrefix;
    @JsonProperty("LMReceiverMobileNumber")
    private String LMReceiverMobileNumber;
    @JsonProperty("LMReceiverNationality")
    private String LMReceiverNationality;
    @JsonProperty("LMSendPurposeOfTransfer")
    private String LMSendPurposeOfTransfer;
    @JsonProperty("LMSourceOfIncome")
    private String LMSourceOfIncome;
    @JsonProperty("LMDeliveryOption")
    private String LMDeliveryOption;
    @JsonProperty("LMSendChannel")
    private String LMSendChannel;
    @JsonProperty("LMSendAudience")
    private String LMSendAudience;
    @JsonProperty("LMReceiveAgentCode")
    private String LMReceiveAgentCode;
    @JsonProperty("LMSendCurrency")
    private String LMSendCurrency;
    @JsonProperty("LMReceiveCurrency")
    private String LMReceiveCurrency;
    @JsonProperty("LMAmount")
    private String LMAmount;
    @JsonProperty("LMSenderGender")
    private String LMSenderGender;
    @JsonProperty("LMRelationshipWithReceiverList")
    private String LMRelationshipWithReceiverList;
    @JsonProperty("LMRelationshipWithReceiver")
    private String LMRelationshipWithReceiver;
    @JsonProperty("LMSenderPlaceOfBirth")
    private String LMSenderPlaceOfBirth;
    @JsonProperty("LMSenderCountryOfBirth")
    private String LMSenderCountryOfBirth;
    @JsonProperty("LMSenderOccupation")
    private String LMSenderOccupation;
    @JsonProperty("LMSenderOccupationList")
    private String LMSenderOccupationList;
    @JsonProperty("LMSenderIDIssueDate")
    private String LMSenderIDIssueDate;
    @JsonProperty("LMSenderIDExpiryDate")
    private String LMSenderIDExpiryDate;
    @JsonProperty("LMSenderSecondaryIDType")
    private String LMSenderSecondaryIDType;
    @JsonProperty("LMSenderSecondaryIDNumber")
    private String LMSenderSecondaryIDNumber;
    @JsonProperty("LMSenderProofOfFund")
    private String LMSenderProofOfFund;
    @JsonProperty("LMSenderIDIssuanceCountry")
    private String LMSenderIDIssuanceCountry;
    @JsonProperty("LMSenderFamilyName")
    private String LMSenderFamilyName;
    @JsonProperty("LMSendPaymentMode")
    private String LMSendPaymentMode;
    @JsonProperty("LMReceiverState")
    private String LMReceiverState;
    @JsonProperty("LMReceiverLandmark")
    private String LMReceiverLandmark;
    @JsonProperty("LMReceiverZipCode")
    private String LMReceiverZipCode;
    @JsonProperty("LMReceiverIDType")
    private String LMReceiverIDType;
    @JsonProperty("LMReceiverIDName")
    private String LMReceiverIDName;
    @JsonProperty("LMReceiverIDNumber")
    private String LMReceiverIDNumber;
    @JsonProperty("LMReceiverDOB")
    private String LMReceiverDOB;
    @JsonProperty("LMReceiverOtherName")
    private String LMReceiverOtherName;
    @JsonProperty("LMReceiverProofOfAddress")
    private String LMReceiverProofOfAddress;
    @JsonProperty("LMRelationshipWithSender")
    private String LMRelationshipWithSender;
    @JsonProperty("LMReceiverMediumOfPayment")
    private String LMReceiverMediumOfPayment;
    @JsonProperty("LMReceiverPurposeOfTransaction")
    private String LMReceiverPurposeOfTransaction;
    @JsonProperty("LMReceiverIDIssueDate")
    private String LMReceiverIDIssueDate;
    @JsonProperty("LMReceiverIDExpiryDate")
    private String LMReceiverIDExpiryDate;
    @JsonProperty("LMReceiverSecondaryIDType")
    private String LMReceiverSecondaryIDType;
    @JsonProperty("LMReceiverSecondaryIDNumber")
    private String LMReceiverSecondaryIDNumber;
    @JsonProperty("LMReceiverIDIssuanceCountry")
    private String LMReceiverIDIssuanceCountry;
    @JsonProperty("LMReceiverAccountType")
    private String LMReceiverAccountType;
    @JsonProperty("LMReceiverIBAN")
    private String LMReceiverIBAN;
    @JsonProperty("LMReceiverAccountNumber")
    private String LMReceiverAccountNumber;
    @JsonProperty("LMReceiverMobileWalletNumber")
    private String LMReceiverMobileWalletNumber;
    @JsonProperty("LMReceiverBankIdentificationCode")
    private String LMReceiverBankIdentificationCode;
    @JsonProperty("LMReceiverBankSubCode")
    private String LMReceiverBankSubCode;
    @JsonProperty("LMReceiverIdentificationNumber")
    private String LMReceiverIdentificationNumber;
    @JsonProperty("LMReceivingInstitutionList")
    private String LMReceivingInstitutionList;
    @JsonProperty("LMReceivingInstitution")
    private String LMReceivingInstitution;
    @JsonProperty("LMReceiverBranchName")
    private String LMReceiverBranchName;
    @JsonProperty("LMReceiverBranchAddress")
    private String LMReceiverBranchAddress;
    @JsonProperty("LMReceiverBranchCity")
    private String LMReceiverBranchCity;
    @JsonProperty("LMReceiverCardNumber")
    private String LMReceiverCardNumber;
    @JsonProperty("LMBillerCode")
    private String LMBillerCode;
    @JsonProperty("LMBillerAccountNumber")
    private String LMBillerAccountNumber;
    @JsonProperty("LMBillerAgent")
    private String LMBillerAgent;
    @JsonProperty("LMSessionID")
    private String LMSessionID;
    @JsonProperty("LMRespUrl")
    private String LMRespUrl;
    @JsonProperty("LMUserId")
    private String LMUserId;
    @JsonProperty("LMPassword")
    private String LMPassword;
    @JsonProperty("LMSenderCd")
    private String LMSenderCd;
    @JsonProperty("LMTransactionDate")
    private String LMTransactionDate;
    @JsonProperty("LMTransactionReferenceNumber")
    private String LMTransactionReferenceNumber;
    @JsonProperty("LMSenderAccountNumber")
    private String LMSenderAccountNumber;
    @JsonProperty("LMSenderBankName")
    private String LMSenderBankName;
    @JsonProperty("LMSwiftCode")
    private String LMSwiftCode;
    @JsonProperty("LMRemarks")
    private String LMRemarks;
    @JsonProperty("LMAlternativePayment")
    private String LMAlternativePayment;
    @JsonProperty("LMSecondaryAction")
    private String LMSecondaryAction;
    @JsonProperty("LMRoutingCode")
    private String LMRoutingCode;
}

