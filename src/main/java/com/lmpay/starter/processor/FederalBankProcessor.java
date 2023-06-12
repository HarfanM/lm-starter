package com.lmpay.starter.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lmpay.starter.config.AppProperties;
import com.lmpay.starter.helper.Helper;
import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.model.GenericResponse;
import com.lmpay.starter.model.PartnerTransaction;
import com.lmpay.starter.repository.PartnerTransactionRepository;
import com.lmpay.starter.service.BankService;
import io.github.jamsesso.jsonlogic.JsonLogic;
import io.github.jamsesso.jsonlogic.JsonLogicException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FederalBankProcessor implements BankService {

    private final AppProperties appProperties;
    private final RestTemplate restTemplate;
    private final PartnerTransactionRepository partnerTransactionRepository;
    private static final Logger log = LoggerFactory.getLogger(FederalBankProcessor.class);
    Timestamp time = new Timestamp(Instant.now().toEpochMilli());
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Format the LocalDate using the formatter
    String formattedDate = date.format(formatter);
    @Autowired
    public FederalBankProcessor(AppProperties appProperties,
                               RestTemplate restTemplate,
                               PartnerTransactionRepository partnerTransactionRepository) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
        this.partnerTransactionRepository = partnerTransactionRepository;
    }

    @Override
    public String confirmTransaction(GenericRequest request) {
        return null;
    }

    @Override
    public String bookTransaction(GenericRequest request) throws JsonLogicException {
        JsonLogic jsonLogic = new JsonLogic();
        String neftCheck = "{\"==\": [{\"var\": \"LMAlternativePayment\"}, \"\"]}";
        String impsCheck = "{\"==\": [{\"var\": \"LMAlternativePayment\"}, \"Y\"]}";

        Map<String, String> data = new HashMap<>();
        data.put("LMAlternativePayment", request.getLMAlternativePayment());

        boolean neftResult = (boolean) jsonLogic.apply(neftCheck, data);
        boolean impsResult = (boolean) jsonLogic.apply(impsCheck, data);

        request.setLMTransactionReferenceNumber(partnerTransactionRepository.generateTransactionNo());
        request.setLMRespUrl(appProperties.respUrl);
        request.setLMUserId(appProperties.userId);
        request.setLMPassword(appProperties.userPassword);
        request.setLMSenderCd(appProperties.sendercd);
        request.setLMTransactionDate(formattedDate);

        if (neftResult) {
            return loadingPayload(appProperties.neftUrl, request, "transactionjsonfederalneft");
        } else if (impsResult) {
            return loadingPayload(appProperties.impsUrl, request, "transactionjsonfederalimps");
        } else {
            return loadingPayload(appProperties.intraUrl, request, "transactionjsonfederalintra");
        }
    }

    private String loadingPayload(String url, GenericRequest request, String requestType) {
        Instant startTime = Instant.now();
        log.info("Requesting federal Bank for url {}", url);

        ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        String jsonRequest;
        try {
            jsonRequest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);

            String jsonResult;
            if(Objects.equals(requestType, "transactionjsonfederalneft")) {
                jsonResult = jsonProcessNeft(jsonRequest, requestType);
            }
            else if(Objects.equals(requestType, "transactionjsonfederalimps")) {
                jsonResult = jsonProcessImps(jsonRequest, requestType);
            }
            else {
                jsonResult = jsonProcessIntra(jsonRequest, requestType);
            }
            Helper.init(appProperties, restTemplate);

            String response = Helper.httpWithCertificates(url, jsonResult);
            if (!response.isEmpty()) {
                GenericResponse genericResponse = new GenericResponse();
                try {
                    JsonNode rootNode = mapper.readTree(response);
                    if(!Objects.equals(requestType, "transactionjsonfederalimps")){
                        genericResponse.setLMReceiveagentName("FederalBank");
                        genericResponse.setLMTransactionReferenceNo(rootNode.get("RefId").asText());
                        genericResponse.setLMTransactionDate(rootNode.get("TranTime").asText());
                    }
                    genericResponse.setLMResponseMessage(rootNode.get("Reason").asText());
                    genericResponse.setLMTransactionReferenceNo(rootNode.get("ReferenceId").asText());
                    genericResponse.setLMTransactionDate(rootNode.get("TranDate").asText());
                    genericResponse.setLMReceiveagentServiceName(request.getLMDeliveryOption());
                    genericResponse.setLMSendPrincipleAmount(request.getLMAmount());
                    genericResponse.setLMSendCurrency(request.getLMSendCurrency());

                    genericResponse.setLMReceiveagentCode("");
                    genericResponse.setLMSendFee("");
                    genericResponse.setLMSendpartnerFee("");
                    genericResponse.setLMSendReceivePartnerFee("");
                    genericResponse.setLMSendCharge("");
                    genericResponse.setLMSendTax("");
                    genericResponse.setLMSendTotalCharges("");
                    genericResponse.setLMSendDiscount("");
                    genericResponse.setLMSendNetAmount("");
                    genericResponse.setLMSendSettlementAmount("");
                    genericResponse.setLMReceiveAmount("");
                    genericResponse.setLMReceiveFee("");
                    genericResponse.setLMReceiveTax("");
                    genericResponse.setLMNetReceiveAmount("");
                    genericResponse.setLMExchangeRate("");
                    genericResponse.setLMIndicativeExchangeRate("");
                    genericResponse.setLMIndicativeCurrency("");
                    genericResponse.setLMReceiveCurrency("");
                    genericResponse.setLMSendSettlementCurrency("");
                    genericResponse.setLMReceiveSettlementCurrency("");
                    genericResponse.setLMSendSettlementAmountToLM("");
                    genericResponse.setLMTotalSettlementAmountToLM("");
                    genericResponse.setLMReceiveSettlementAmountFromLM("");
                    genericResponse.setLMTotalReceiveSettlementAmountFromLM("");
                    genericResponse.setLMPartnerCode("");
                    genericResponse.setLMPayoutPartnerName("");
                    genericResponse.setLMPayinPartnerRef("");

                    ObjectMapper nonNullMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
                    nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                    log.info("Response saving in db...");
                    Instant endTime = Instant.now();
                    Duration duration = Duration.between(startTime, endTime);
                    long responseTimeMillis = duration.toMillis();

                    JSONObject bankRequestMap = new JSONObject(mapper.writeValueAsString(request));
                    JSONObject bankResponseMap = new JSONObject(mapper.writeValueAsString(genericResponse));

                    PartnerTransaction partnerTransaction = new PartnerTransaction();
                    if(!Objects.equals(requestType, "transactionjsonfederalimps")){
                        partnerTransaction.setTransactionNo(rootNode.get("RefId").asText());
                        partnerTransaction.setPartnerReferenceNo1(rootNode.get("RefId").asText());
                    }
                    partnerTransaction.setTransactionNo(rootNode.get("ReferenceId").asText());
                    partnerTransaction.setPartnerReferenceNo1(rootNode.get("ReferenceId").asText());
                    partnerTransaction.setTransactionType(request.getLMDeliveryOption());
                    partnerTransaction.setPartnerName("FederalBank");
                    partnerTransaction.setBankName("FederalBank");
                    partnerTransaction.setProcessor("FederalBank Processor");
                    if(request.getLMReceiverBankIdentificationCode() != null) {
                        partnerTransaction.setBankRoutingCode(request.getLMReceiverBankIdentificationCode());
                    }
                    partnerTransaction.setSendingCurrency(request.getLMSendCurrency());
                    partnerTransaction.setReceivingCurrency(request.getLMReceiveCurrency());
                    partnerTransaction.setTransactionDate(time);
                    partnerTransaction.setAmount(Integer.valueOf(request.getLMAmount()));
                    partnerTransaction.setRetryAttempts(0);
                    partnerTransaction.setPartnerRequest(String.valueOf(bankRequestMap));
                    partnerTransaction.setPartnerResponse(String.valueOf(bankResponseMap));
                    partnerTransaction.setTransactionStatus(rootNode.get("Reason").asText());
                    partnerTransaction.setResponseTime(String.valueOf(responseTimeMillis));
                    partnerTransaction.setCreatedOn(time);
                    partnerTransaction.setCreatedBy("User");
                    partnerTransaction.setUpdatedOn(time);
                    partnerTransaction.setUpdatedBy("User");

                    // Pass the partnerTransaction as a parameter in a method
                    partnerTransactionRepository.save(partnerTransaction);
                    log.info("Response saved in db...");

                    return nonNullMapper.writeValueAsString(genericResponse);
                } catch (Exception e) {
                    log.error("Error parsing response JSON: {}", e.getMessage());
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error loading payload: {}", e.getMessage());
            return null;
        }
    }

    public String jsonProcessImps(String request, String requestType) throws Exception {
        String jsonTxt = getJson();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonTxt);
        JsonNode json1 = objectMapper.readTree(request);

        ObjectNode resultJson = objectMapper.createObjectNode();

        json.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            if (value.isObject()) {
                if (value.isObject() && key.equals("RemmiterDetails")) {
                    ObjectNode remmiterDetails = (ObjectNode) value;
                    remmiterDetails.put("Name", json1.get("LMSenderFirstName").asText());
                    remmiterDetails.put("AccNumber", json1.get("LMSenderAccountNumber").asText());
                    remmiterDetails.put("Address", json1.get("LMSenderAddress").asText());
                    remmiterDetails.put("Country", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankCountry", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankName", json1.get("LMSenderBankName").asText());
                    remmiterDetails.put("SwiftCd", json1.get("LMSwiftCode").asText());
                    resultJson.set(key, remmiterDetails);
                } else if (value.isObject() && key.equals("BeneficiaryDetails")) {
                    ObjectNode beneficiaryDetails = (ObjectNode) value;
                    beneficiaryDetails.put("Name", json1.get("LMReceiverFirstName").asText());
                    beneficiaryDetails.put("AccNumber", json1.get("LMReceiverAccountNumber").asText());
                    beneficiaryDetails.put("Address", json1.get("LMReceiverAddress").asText());
                    beneficiaryDetails.put("IFSC", json1.get("LMReceiverBankIdentificationCode").asText());
                    resultJson.set(key, beneficiaryDetails);
                } else {
                    resultJson.set(key, value);
                }
            } else {
                String elementValue = json1.get(value.asText()).asText();
                if (!elementValue.equals("null")) {
                    resultJson.put(key, elementValue);
                }
            }
        });

        return resultJson.toString();
    }

    public String jsonProcessNeft(String request, String requestType) throws Exception {
        String jsonTxt = getJson();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonTxt);
        JsonNode json1 = objectMapper.readTree(request);

        ObjectNode resultJson = objectMapper.createObjectNode();

        json.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            if (value.isObject()) {
                if (value.isObject() && key.equals("RemmiterDetails")) {
                    ObjectNode remmiterDetails = (ObjectNode) value;
                    remmiterDetails.put("Name", json1.get("LMSenderFirstName").asText());
                    remmiterDetails.put("AccNumber", json1.get("LMSenderAccountNumber").asText());
                    remmiterDetails.put("Address", json1.get("LMSenderAddress").asText());
                    remmiterDetails.put("Country", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankCountry", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankName", json1.get("LMSenderBankName").asText());
                    remmiterDetails.put("SwiftCd", json1.get("LMSwiftCode").asText());
                    resultJson.set(key, remmiterDetails);
                } else if (value.isObject() && key.equals("BeneficiaryDetails")) {
                    ObjectNode beneficiaryDetails = (ObjectNode) value;
                    beneficiaryDetails.put("Name", json1.get("LMReceiverFirstName").asText());
                    beneficiaryDetails.put("AccNumber", json1.get("LMReceiverAccountNumber").asText());
                    beneficiaryDetails.put("Address", json1.get("LMReceiverAddress").asText());
                    beneficiaryDetails.put("AccountType", json1.get("LMReceiverAccountType").asText());
                    beneficiaryDetails.put("IFSC", json1.get("LMReceiverBankIdentificationCode").asText());
                    resultJson.set(key, beneficiaryDetails);
                } else {
                    resultJson.set(key, value);
                }
            } else {
                String elementValue = json1.get(value.asText()).asText();
                if (!elementValue.equals("null")) {
                    resultJson.put(key, elementValue);
                }
            }
        });

        return resultJson.toString();
    }

    private String jsonProcessIntra(String request, String requestType) throws Exception {
        String jsonTxt = getJsonIntra();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonTxt);
        JsonNode json1 = objectMapper.readTree(request);

        ObjectNode resultJson = objectMapper.createObjectNode();

        json.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            if (value.isObject()) {
                if (value.isObject() && key.equals("RemmiterDetails")) {
                    ObjectNode remmiterDetails = (ObjectNode) value;
                    remmiterDetails.put("Name", json1.get("LMSenderFirstName").asText());
                    remmiterDetails.put("AccNumber", json1.get("LMSenderAccountNumber").asText());
                    remmiterDetails.put("Address", json1.get("LMSenderAddress").asText());
                    remmiterDetails.put("Country", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankCountry", json1.get("LMSenderAddressCountry").asText());
                    remmiterDetails.put("BankName", json1.get("LMSenderBankName").asText());
                    remmiterDetails.put("SwiftCd", json1.get("LMSwiftCode").asText());
                    resultJson.set(key, remmiterDetails);
                } else if (value.isObject() && key.equals("BeneficiaryDetails")) {
                    ObjectNode beneficiaryDetails = (ObjectNode) value;
                    beneficiaryDetails.put("Name", json1.get("LMReceiverFirstName").asText());
                    beneficiaryDetails.put("AccNumber", json1.get("LMReceiverAccountNumber").asText());
                    beneficiaryDetails.put("Address", json1.get("LMReceiverAddress").asText());
                    resultJson.set(key, beneficiaryDetails);
                } else {
                    resultJson.set(key, value);
                }
            } else {
                String elementValue = json1.get(value.asText()).asText();
                if (!elementValue.equals("null")) {
                    resultJson.put(key, elementValue);
                }
            }
        });

        return resultJson.toString();
    }

//    public String getJson(String requestType) {
//        String url = Helper.ClientResponse(appProperties.vendorUrl);
//        RestTemplate restTemplate = new RestTemplate();
//        String jsonString = restTemplate.getForObject(Objects.requireNonNull(url), String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode;
//        try {
//            rootNode = objectMapper.readValue(jsonString, JsonNode.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        JsonNode dataNode = rootNode.get("data");
//
//        // Loop through each object in the data array and find the one with the specified requestType
//        for (int i = 0; i < dataNode.size(); i++) {
//            JsonNode objNode = dataNode.get(i);
//            if (objNode.get("requestType").asText().equals(requestType) && objNode.has("FederalRequest")) {
//                return objNode.get("FederalRequest").toString().replace("[", "").replace("]", "");
//            }
//        }
//
//        // If no object is found with the specified requestType and FederalRequest, return an empty object
//        JsonNode resultNode = objectMapper.createObjectNode();
//        return resultNode.toString();
//    }

    private static String getJson() {
        try {
            ClassPathResource resource = new ClassPathResource("json/federal.json");
            return new String(resource.getInputStream().readAllBytes());
        } catch (Exception e) {
            log.error("Error reading JSON file: {}", e.getMessage());
            return null;
        }
    }

    private String getJsonIntra() {
        try {
            ClassPathResource resource = new ClassPathResource("json/federal-intra.json");
            return new String(resource.getInputStream().readAllBytes());
        } catch (Exception e) {
            log.error("Error reading JSON file: {}", e.getMessage());
            return null;
        }
    }
}

