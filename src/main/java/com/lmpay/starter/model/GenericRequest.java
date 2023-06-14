package com.lmpay.starter.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequest {
    @JsonProperty("LMSenderFirstName")
    @NotBlank(message = "The LMSenderFirstName is required.")
    @Size(min = 3, max = 45, message = "The LMSenderFirstName must be from 3 to 45 characters.")
    private String LMSenderFirstName;

    @JsonProperty("LMSenderLastName")
    @NotBlank(message = "The LMSenderLastName is required.")
    @Size(min = 3, max = 45, message = "The LMSenderLastName must be from 3 to 45 characters.")
    private String LMSenderLastName;

    @JsonProperty("LMSenderAddress")
    @NotBlank(message = "The LMSenderAddress is required.")
    @Size(min = 3, max = 100, message = "The LMSenderAddress must be from 3 to 100 characters.")
    private String LMSenderAddress;

    @JsonProperty("LMSenderCity")
    @NotBlank(message = "The LMSenderCity is required.")
    @Size(min = 3, max = 20, message = "The LMSenderCity must be from 3 to 20 characters.")
    private String LMSenderCity;

    @JsonProperty("LMSenderState")
    @NotBlank(message = "The LMSenderState is required.")
    @Size(min = 3, max = 20, message = "The LMSenderState must be from 3 to 20 characters.")
    private String LMSenderState;

    @JsonProperty("LMSenderPostalCode")
    @NotBlank(message = "The LMSenderPostalCode is required.")
    @Size(min = 3, max = 10, message = "The LMSenderPostalCode must be from 3 to 10 characters.")
    private String LMSenderPostalCode;

    @JsonProperty("LMSenderNationality")
    @NotBlank(message = "The LMSenderNationality is required.")
    @Size(min = 2, max = 45, message = "The LMSenderNationality must be from 2 to 45 characters.")
    private String LMSenderNationality;

    @JsonProperty("LMSenderIDType")
    @NotBlank(message = "The LMSenderIDType is required.")
    @Size(min = 2, max = 45, message = "The LMSenderIDType must be from 2 to 45 characters.")
    private String LMSenderIDType;

    @JsonProperty("LMSenderIDNumber")
    @NotBlank(message = "The LMSenderIDNumber is required.")
    @Size(min = 3, max = 45, message = "The LMSenderIDNumber must be from 3 to 45 characters.")
    private String LMSenderIDNumber;

    @JsonProperty("LMSenderAddressCountry")
    @NotBlank(message = "The LMSenderAddressCountry is required.")
    @Size(min = 3, max = 45, message = "The LMSenderAddressCountry must be from 3 to 45 characters.")
    private String LMSenderAddressCountry;

    @JsonProperty("LMSenderDOB")
    @NotBlank(message = "The LMSenderDOB is required.")
    @Size(min = 3, max = 45, message = "The LMSenderDOB must be from 3 to 45 characters.")
    private String LMSenderDOB;

    @JsonProperty("LMSenderMobileISDPrefix")
    @NotBlank(message = "The LMSenderMobileISDPrefix is required.")
    @Size(min = 1, max = 10, message = "The LMSenderMobileISDPrefix must be from 1 to 10 characters.")
    private String LMSenderMobileISDPrefix;

    @JsonProperty("LMSenderMobileNumber")
    @NotBlank(message = "The LMSenderMobileNumber is required.")
    @Size(min = 3, max = 45, message = "The LMSenderMobileNumber must be from 3 to 45 characters.")
    private String LMSenderMobileNumber;

    @JsonProperty("LMDestinationCountry")
    @NotBlank(message = "The LMDestinationCountry is required.")
    @Size(min = 2, max = 45, message = "The LMDestinationCountry must be from 2 to 45 characters.")
    private String LMDestinationCountry;

    @JsonProperty("LMReceiverFirstName")
    @NotBlank(message = "The LMReceiverFirstName is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverFirstName must be from 3 to 45 characters.")
    private String LMReceiverFirstName;

    @JsonProperty("LMReceiverLastName")
    @NotBlank(message = "The LMReceiverLastName is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverLastName must be from 3 to 45 characters.")
    private String LMReceiverLastName;

    @JsonProperty("LMReceiverAddress")
    @NotBlank(message = "The LMReceiverAddress is required.")
    @Size(min = 3, max = 100, message = "The LMReceiverAddress must be from 3 to 100 characters.")
    private String LMReceiverAddress;

    @JsonProperty("LMReceiverCity")
    @NotBlank(message = "The LMReceiverCity is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverCity must be from 3 to 45 characters.")
    private String LMReceiverCity;

    @JsonProperty("LMReceiverMobileISDPrefix")
    @NotBlank(message = "The LMReceiverMobileISDPrefix is required.")
    @Size(min = 1, max = 10, message = "The LMReceiverMobileISDPrefix must be from 1 to 10 characters.")
    private String LMReceiverMobileISDPrefix;

    @JsonProperty("LMReceiverMobileNumber")
    @NotBlank(message = "The LMReceiverMobileNumber is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverMobileNumber must be from 3 to 45 characters.")
    private String LMReceiverMobileNumber;

    @JsonProperty("LMReceiverNationality")
    @NotBlank(message = "The LMReceiverNationality is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverNationality must be from 3 to 45 characters.")
    private String LMReceiverNationality;

    @JsonProperty("LMSendPurposeOfTransfer")
    @NotBlank(message = "The LMSendPurposeOfTransfer is required.")
    @Size(min = 3, max = 100, message = "The LMSendPurposeOfTransfer must be from 3 to 100 characters.")
    private String LMSendPurposeOfTransfer;

    @JsonProperty("LMSourceOfIncome")
    @NotBlank(message = "The LMSourceOfIncome is required.")
    @Size(min = 3, max = 100, message = "The LMSourceOfIncome must be from 3 to 100 characters.")
    private String LMSourceOfIncome;

    @JsonProperty("LMDeliveryOption")
    @NotBlank(message = "The LMDeliveryOption is required.")
    @Size(min = 3, max = 20, message = "The LMDeliveryOption must be from 3 to 20 characters.")
    private String LMDeliveryOption;

    @JsonProperty("LMSendChannel")
    @NotBlank(message = "The LMSendChannel is required.")
    @Size(min = 1, max = 30, message = "The LMSendChannel must be between 1 and 30 characters.")
    private String LMSendChannel;

    @JsonProperty("LMSendAudience")
    @NotBlank(message = "The LMSendAudience is required.")
    @Size(min = 1, max = 30, message = "The LMSendAudience must be between 1 and 30 characters.")
    private String LMSendAudience;

    @JsonProperty("LMReceiveAgentCode")
    @NotBlank(message = "The LMReceiveAgentCode is required.")
    @Size(min = 1, max = 20, message = "The LMReceiveAgentCode must be between 1 and 20 characters.")
    private String LMReceiveAgentCode;

    @JsonProperty("LMSendCurrency")
    @NotBlank(message = "The LMSendCurrency is required.")
    @Size(min = 2, max = 3, message = "The LMSendCurrency must be between 2 and 3 characters.")
    private String LMSendCurrency;

    @JsonProperty("LMReceiveCurrency")
    @NotBlank(message = "The LMReceiveCurrency is required.")
    @Size(min = 2, max = 3, message = "The LMReceiveCurrency must be between 2 and 3 characters.")
    private String LMReceiveCurrency;

    @JsonProperty("LMAmount")
    @NotBlank(message = "The LMAmount is required.")
    @Size(min = 1, max = 20, message = "The LMAmount must be between 1 and 20 characters.")
    private String LMAmount;

    @JsonProperty("LMSenderGender")
//    @NotBlank(message = "The LMSenderGender is required.")
    @Size(min = 1, max = 1, message = "The LMSenderGender must be between 1 and 1 characters.")
    private String LMSenderGender;

    @JsonProperty("LMRelationshipWithReceiverList")
//    @NotBlank(message = "The LMRelationshipWithReceiverList is required.")
    @Size(min = 1, max = 255, message = "The LMRelationshipWithReceiverList must be between 1 and 255 characters.")
    private String LMRelationshipWithReceiverList;

    @JsonProperty("LMRelationshipWithReceiver")
//    @NotBlank(message = "The LMRelationshipWithReceiver is required.")
    @Size(min = 1, max = 50, message = "The LMRelationshipWithReceiver must be between 1 and 50 characters.")
    private String LMRelationshipWithReceiver;

    @JsonProperty("LMSenderPlaceOfBirth")
//    @NotBlank(message = "The LMSenderPlaceOfBirth is required.")
    @Size(min = 1, max = 30, message = "The LMSenderPlaceOfBirth must be between 1 and 30 characters.")
    private String LMSenderPlaceOfBirth;

    @JsonProperty("LMSenderCountryOfBirth")
//    @NotBlank(message = "The LMSenderCountryOfBirth is required.")
    @Size(min = 1, max = 30, message = "The LMSenderCountryOfBirth must be between 1 and 30 characters.")
    private String LMSenderCountryOfBirth;

    @JsonProperty("LMSenderOccupation")
//    @NotBlank(message = "The LMSenderOccupation is required.")
    @Size(min = 1, max = 30, message = "The LMSenderOccupation must be between 1 and 30 characters.")
    private String LMSenderOccupation;

    @JsonProperty("LMSenderOccupationList")
//    @NotBlank(message = "The LMSenderOccupationList is required.")
    @Size(min = 1, max = 30, message = "The LMSenderOccupationList must be between 1 and 30 characters.")
    private String LMSenderOccupationList;

    @JsonProperty("LMSenderIDIssueDate")
//    @NotNull(message = "The LMSenderIDIssueDate is required.")
    @Past(message = "The LMSenderIDIssueDate date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date LMSenderIDIssueDate;

    @JsonProperty("LMSenderIDExpiryDate")
//    @NotNull(message = "The LMSenderIDIssueDate is required.")
    @Future(message = "The LMSenderIDExpiryDate date must be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date LMSenderIDExpiryDate;

    @JsonProperty("LMSenderSecondaryIDType")
    @NotBlank(message = "The LMSenderSecondaryIDType is required.")
    @Size(min = 1, max = 30, message = "The LMSenderSecondaryIDType must be between 1 and 30 characters.")
    private String LMSenderSecondaryIDType;

    @JsonProperty("LMSenderSecondaryIDNumber")
//    @NotBlank(message = "The LMSenderSecondaryIDNumber is required.")
    @Size(min = 1, max = 30, message = "The LMSenderSecondaryIDNumber must be between 1 and 30 characters.")
    private String LMSenderSecondaryIDNumber;

    @JsonProperty("LMSenderProofOfFund")
//    @NotBlank(message = "The LMSenderProofOfFund is required.")
    @Size(min = 4, max = 255, message = "The LMSenderProofOfFund must be between 4 and 255 characters.")
    private String LMSenderProofOfFund;

    @JsonProperty("LMSenderIDIssuanceCountry")
//    @NotBlank(message = "The LMSenderIDIssuanceCountry is required.")
    @Size(min = 1, max = 30, message = "The LMSenderIDIssuanceCountry must be between 1 and 30 characters.")
    private String LMSenderIDIssuanceCountry;

    @JsonProperty("LMSenderFamilyName")
//    @NotBlank(message = "The LMSenderFamilyName is required.")
    @Size(min = 1, max = 30, message = "The LMSenderFamilyName must be between 1 and 30 characters.")
    private String LMSenderFamilyName;

    @JsonProperty("LMSendPaymentMode")
//    @NotBlank(message = "The LMSendPaymentMode is required.")
    @Size(min = 1, max = 30, message = "The LMSendPaymentMode must be between 1 and 30 characters.")
    private String LMSendPaymentMode;

    @JsonProperty("LMReceiverState")
//    @NotBlank(message = "The LMReceiverState is required.")
    @Size(min = 1, max = 30, message = "The LMReceiverState must be between 1 and 30 characters.")
    private String LMReceiverState;

    @JsonProperty("LMReceiverLandmark")
//    @NotBlank(message = "The LMReceiverLandmark is required.")
    @Size(min = 1, max = 30, message = "The LMReceiverLandmark must be between 1 and 30 characters.")
    private String LMReceiverLandmark;

    @JsonProperty("LMReceiverZipCode")
//    @NotBlank(message = "The LMReceiverZipCode is required.")
    @Size(min = 1, max = 10, message = "The LMReceiverZipCode must be between 1 and 10 characters.")
    private String LMReceiverZipCode;

    @JsonProperty("LMReceiverIDType")
//    @NotBlank(message = "The LMReceiverIDType is required.")
    @Size(min = 1, max = 30, message = "The LMReceiverIDType must be between 1 and 30 characters.")
    private String LMReceiverIDType;

    @JsonProperty("LMReceiverIDName")
//    @NotBlank(message = "The LMReceiverIDName is required.")
    @Size(min = 1, max = 45, message = "The LMReceiverIDName must be between 1 and 45 characters.")
    private String LMReceiverIDName;

    @JsonProperty("LMReceiverIDNumber")
//    @NotBlank(message = "The LMReceiverIDNumber is required.")
    @Size(min = 1, max = 25, message = "The LMReceiverIDNumber must be between 1 and 25 characters.")
    private String LMReceiverIDNumber;

    @JsonProperty("LMReceiverDOB")
//    @NotNull(message = "The LMReceiverDOB is required.")
    @Past(message = "The LMReceiverDOB date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date LMReceiverDOB;

    @JsonProperty("LMReceiverOtherName")
//    @NotBlank(message = "The LMReceiverOtherName is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverOtherName must be from 3 to 45 characters.")
    private String LMReceiverOtherName;

    @JsonProperty("LMReceiverProofOfAddress")
//    @NotBlank(message = "The LMReceiverProofOfAddress is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverProofOfAddress must be from 3 to 45 characters.")
    private String LMReceiverProofOfAddress;

    @JsonProperty("LMRelationshipWithSender")
//    @NotBlank(message = "The LMRelationshipWithSender is required.")
    @Size(min = 3, max = 45, message = "The LMRelationshipWithSender must be from 3 to 45 characters.")
    private String LMRelationshipWithSender;

    @JsonProperty("LMReceiverMediumOfPayment")
//    @NotBlank(message = "The LMReceiverMediumOfPayment is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverMediumOfPayment must be from 3 to 45 characters.")
    private String LMReceiverMediumOfPayment;

    @JsonProperty("LMReceiverPurposeOfTransaction")
//    @NotBlank(message = "The LMReceiverPurposeOfTransaction is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverPurposeOfTransaction must be from 3 to 45 characters.")
    private String LMReceiverPurposeOfTransaction;

    @JsonProperty("LMReceiverIDIssueDate")
//    @NotNull(message = "The LMReceiverIDIssueDate is required.")
    @Past(message = "The LMReceiverIDIssueDate date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date LMReceiverIDIssueDate;

    @JsonProperty("LMReceiverIDExpiryDate")
//    @NotNull(message = "The LMReceiverIDExpiryDate is required.")
    @Future(message = "The LMReceiverIDExpiryDate date must be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date LMReceiverIDExpiryDate;

    @JsonProperty("LMReceiverSecondaryIDType")
//    @NotBlank(message = "The LMReceiverSecondaryIDType is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverSecondaryIDType must be from 3 to 45 characters.")
    private String LMReceiverSecondaryIDType;

    @JsonProperty("LMReceiverSecondaryIDNumber")
//    @NotBlank(message = "The LMReceiverSecondaryIDNumber is required.")
    @Size(min = 3, max = 20, message = "The LMReceiverSecondaryIDNumber must be from 3 to 20 characters.")
    private String LMReceiverSecondaryIDNumber;

    @JsonProperty("LMReceiverIDIssuanceCountry")
//    @NotBlank(message = "The LMReceiverIDIssuanceCountry is required.")
    @Size(min = 3, max = 30, message = "The LMReceiverIDIssuanceCountry must be from 3 to 30 characters.")
    private String LMReceiverIDIssuanceCountry;

    @JsonProperty("LMReceiverAccountType")
//    @NotBlank(message = "The LMReceiverAccountType is required.")
    @Size(min = 3, max = 10, message = "The LMReceiverAccountType must be from 3 to 10 characters.")
    private String LMReceiverAccountType;

    @JsonProperty("LMReceiverIBAN")
//    @NotBlank(message = "The LMReceiverIBAN is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverIBAN must be from 3 to 45 characters.")
    private String LMReceiverIBAN;

    @JsonProperty("LMReceiverAccountNumber")
//    @NotBlank(message = "The LMReceiverAccountNumber is required.")
    @Size(min = 3, max = 20, message = "The LMReceiverAccountNumber must be from 3 to 20 characters.")
    private String LMReceiverAccountNumber;

    @JsonProperty("LMReceiverMobileWalletNumber")
//    @NotBlank(message = "The LMReceiverMobileWalletNumber is required.")
    @Size(min = 3, max = 20, message = "The LMReceiverMobileWalletNumber must be from 3 to 20 characters.")
    private String LMReceiverMobileWalletNumber;

    @JsonProperty("LMReceiverBankIdentificationCode")
//    @NotBlank(message = "The LMReceiverBankIdentificationCode is required.")
    @Size(max = 20, message = "The LMReceiverBankIdentificationCode must be max 20 characters.")
    private String LMReceiverBankIdentificationCode;

    @JsonProperty("LMReceiverBankSubCode")
//    @NotBlank(message = "The LMReceiverBankSubCode is required.")
    @Size(min = 3, max = 10, message = "The LMReceiverBankSubCode must be from 3 to 10 characters.")
    private String LMReceiverBankSubCode;

    @JsonProperty("LMReceiverIdentificationNumber")
//    @NotBlank(message = "The LMReceiverIdentificationNumber is required.")
    @Size(min = 3, max = 20, message = "The LMReceiverIdentificationNumber must be from 3 to 20 characters.")
    private String LMReceiverIdentificationNumber;

    @JsonProperty("LMReceivingInstitutionList")
//    @NotBlank(message = "The LMReceivingInstitutionList is required.")
    @Size(min = 3, max = 45, message = "The LMReceivingInstitutionList must be from 3 to 45 characters.")
    private String LMReceivingInstitutionList;

    @JsonProperty("LMReceivingInstitution")
//    @NotBlank(message = "The LMReceivingInstitution is required.")
    @Size(min = 3, max = 45, message = "The LMReceivingInstitution must be from 3 to 45 characters.")
    private String LMReceivingInstitution;

    @JsonProperty("LMReceiverBranchName")
//    @NotBlank(message = "The LMReceiverBranchName is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverBranchName must be from 3 to 45 characters.")
    private String LMReceiverBranchName;

    @JsonProperty("LMReceiverBranchAddress")
//    @NotBlank(message = "The LMReceiverBranchAddress is required.")
    @Size(min = 3, max = 45, message = "The LMReceiverBranchAddress must be from 3 to 45 characters.")
    private String LMReceiverBranchAddress;

    @JsonProperty("LMReceiverBranchCity")
//    @NotBlank(message = "The LMReceiverBranchCity is required.")
    @Size(min = 3, max = 20, message = "The LMReceiverBranchCity must be from 3 to 20 characters.")
    private String LMReceiverBranchCity;

    @JsonProperty("LMReceiverCardNumber")
//    @NotBlank(message = "The LMReceiverCardNumber is required.")
    @Size(min = 3, max = 30, message = "The LMReceiverCardNumber must be from 3 to 30 characters.")
    private String LMReceiverCardNumber;

    @JsonProperty("LMBillerCode")
//    @NotBlank(message = "The LMBillerCode is required.")
    @Size(min = 3, max = 20, message = "The LMBillerCode must be from 3 to 20 characters.")
    private String LMBillerCode;

    @JsonProperty("LMBillerAccountNumber")
//    @NotBlank(message = "The LMBillerAccountNumber is required.")
    @Size(min = 3, max = 20, message = "The LMBillerAccountNumber must be from 3 to 20 characters.")
    private String LMBillerAccountNumber;

    @JsonProperty("LMBillerAgent")
//    @NotBlank(message = "The LMBillerAgent is required.")
    @Size(min = 3, max = 45, message = "The LMBillerAgent must be from 3 to 45 characters.")
    private String LMBillerAgent;

//    @NotBlank(message = "The LMSenderBankName is required.")
    @Size(min = 3, max = 45, message = "The LMSenderBankName must be from 3 to 45 characters.")
    @JsonProperty("LMSenderBankName")
    private String LMSenderBankName;

//    @NotBlank(message = "The LMSwiftCode is required.")
    @Size(min = 1, max = 5, message = "The LMSwiftCode must be from 1 to 5 characters.")
    @JsonProperty("LMSwiftCode")
    private String LMSwiftCode;

//    @NotBlank(message = "The LMPurposeCode is required.")
    @Size(min = 3, max = 10, message = "The LMPurposeCode must be from 3 to 10 characters.")
    @JsonProperty("LMPurposeCode")
    private String LMPurposeCode;

//    @NotBlank(message = "The LMAlternativePayment is required.")
    @Size(min = 1, max = 1, message = "The LMAlternativePayment must be from 3 to 45 characters.")
    @JsonProperty("LMAlternativePayment")
    private String LMAlternativePayment;

    @Size(min = 3, max = 3, message = "The LMReceiverNationalityISOCode must be from 3 to 45 characters.")
    @JsonProperty("LMReceiverNationalityISOCode")
    private String LMReceiverNationalityISOCode;

    @Size(min = 2, max = 2, message = "The LMSenderIdCountryISOCode must be from 3 to 45 characters.")
    @JsonProperty("LMSenderIdCountryISOCode")
    private String LMSenderIdCountryISOCode;

    @Size(min = 3, max = 3, message = "The LMSenderNationalityCountryISOCode must be from 3 to 45 characters.")
    @JsonProperty("LMSenderNationalityCountryISOCode")
    private String LMSenderNationalityCountryISOCode;

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
    @JsonProperty("LMSecondaryAction")
    private String LMSecondaryAction;
    @JsonProperty("LMRoutingCode")
    private String LMRoutingCode;
}

