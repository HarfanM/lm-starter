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
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

@Service
public class HelloPaisaProcessor implements BankService {

    private final AppProperties appProperties;
    private final RestTemplate restTemplate;
    private final PartnerTransactionRepository partnerTransactionRepository;
    private static final Logger log = LoggerFactory.getLogger(HelloPaisaProcessor.class);
    Timestamp time = new Timestamp(Instant.now().toEpochMilli());

    @Autowired
    public HelloPaisaProcessor(AppProperties appProperties,
                               RestTemplate restTemplate,
                               PartnerTransactionRepository partnerTransactionRepository) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
        this.partnerTransactionRepository = partnerTransactionRepository;
    }

    @Override
    public String bookTransaction(GenericRequest request) throws IOException {
        request.setLMTransactionReferenceNumber(partnerTransactionRepository.generateTransactionNo());
        request.setLMSecondaryAction("GetQuote");
        request.setLMUserId(appProperties.dcmUserId);
        request.setLMRoutingCode(appProperties.routingCode);
        return postResponse(request, appProperties.postTransactionUrl, "getquote");
    }

    @Override
    public String confirmTransaction(GenericRequest request) throws IOException {
        request.setLMSecondaryAction("confirmQuote");
        request.setLMUserId(appProperties.dcmUserId);
        request.setLMRoutingCode(appProperties.routingCode);
        return postResponse(request, appProperties.postTransactionUrl, "confirmquote");
    }

    private String postResponse(GenericRequest request, String url, String requestType) throws IOException {
        Instant startTime = Instant.now();

        log.info("Requesting Hello Paisa URL {}", url);

        ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String jsonResult = jsonProcess(jsonRequest);

        Helper.init(appProperties, restTemplate);

        // Process the response
        Response response = Helper.httpBasicAuth(jsonResult, url, "POST");
        if (response.isSuccessful()) {
            try {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String responseJson = responseBody.string();
                    GenericResponse genericResponse = new GenericResponse();
                    try {
                        JsonNode rootNode = mapper.readTree(responseJson);
                        JsonNode dcmResponseNode = rootNode.get("DcmResponse");

                        genericResponse.setLMResponseCode(dcmResponseNode.get("responseCode").asInt());
                        genericResponse.setLMResponseMessage(dcmResponseNode.get("responseMessage").asText());
                        genericResponse.setLMReceiveagentName("HelloPaisa");
                        genericResponse.setLMReceiveagentServiceName(request.getLMDeliveryOption());
                        genericResponse.setLMSendPrincipleAmount(request.getLMAmount());
                        genericResponse.setLMSendCurrency(request.getLMSendCurrency());
                        genericResponse.setLMTransactionReferenceNo(dcmResponseNode.get("payoutPartnerRef").asText());
                        genericResponse.setLMPayoutPartnerName(dcmResponseNode.get("payOutPartnerRefName").asText());
                        genericResponse.setLMPayinPartnerRef(dcmResponseNode.get("payInPartnerRef").asText());
                        if (!Objects.equals(requestType, "getquote")) {
                            genericResponse.setLMTotalReceiveSettlementAmountFromLM(dcmResponseNode.get("DcmTransactionFee").asText());
                            genericResponse.setLMReceiveagentCode(dcmResponseNode.get("DCode").asText());
                            genericResponse.setLMExchangeRate(dcmResponseNode.get("Rate").asText());
                        }

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
                        genericResponse.setLMSendCurrency("");
                        genericResponse.setLMReceiveCurrency("");
                        genericResponse.setLMSendSettlementCurrency("");
                        genericResponse.setLMReceiveSettlementCurrency("");
                        genericResponse.setLMSendSettlementAmountToLM("");
                        genericResponse.setLMTotalSettlementAmountToLM("");
                        genericResponse.setLMReceiveSettlementAmountFromLM("");

                        ObjectMapper nonNullMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
                        nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                        log.info("Response saving in db...");
                        Instant endTime = Instant.now();
                        Duration duration = Duration.between(startTime, endTime);
                        long responseTimeMillis = duration.toMillis();

                        JSONObject bankRequestMap = new JSONObject(mapper.writeValueAsString(request));
                        JSONObject bankResponseMap = new JSONObject(mapper.writeValueAsString(genericResponse));

                        if (!Objects.equals(requestType, "getquote")) {
                            PartnerTransaction partnerTransaction = new PartnerTransaction();
                            partnerTransaction.setTransactionNo(dcmResponseNode.get("payInPartnerRef").asText());
//                            partnerTransaction.setPartnerReferenceNo1(dcmResponseNode.get("payOutPartnerRef").asText());
                            partnerTransaction.setPartnerReferenceNo2(dcmResponseNode.get("DCode").asText());
                            partnerTransaction.setTransactionType(request.getLMDeliveryOption());
                            partnerTransaction.setPartnerName("HelloPaisa");
                            partnerTransaction.setBankName(request.getLMReceiverBranchName());
                            partnerTransaction.setProcessor("HelloPaisa Processor");
                            partnerTransaction.setBankRoutingCode(request.getLMReceiverBankSubCode());
                            partnerTransaction.setSendingCurrency(request.getLMSendCurrency());
                            partnerTransaction.setReceivingCurrency(request.getLMReceiveCurrency());
                            partnerTransaction.setTransactionDate(time);
                            partnerTransaction.setAmount(Integer.valueOf(request.getLMAmount()));
                            partnerTransaction.setRetryAttempts(0);
                            partnerTransaction.setPartnerRequest(String.valueOf(bankRequestMap));
                            partnerTransaction.setPartnerResponse(String.valueOf(bankResponseMap));
                            partnerTransaction.setTransactionStatus(dcmResponseNode.get("responseMessage").asText());
                            partnerTransaction.setResponseTime(String.valueOf(responseTimeMillis));
                            partnerTransaction.setCreatedOn(time);
                            partnerTransaction.setCreatedBy("User");
                            partnerTransaction.setUpdatedOn(time);
                            partnerTransaction.setUpdatedBy("User");

                            // Pass the partnerTransaction as a parameter in a method
                            partnerTransactionRepository.save(partnerTransaction);
                            log.info("Response saved in db...");
                        }

                        return nonNullMapper.writeValueAsString(genericResponse);
                    } catch (Exception e) {
                        log.error("Error parsing response JSON: {}", e.getMessage());
                        return null;
                    }
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


    public String jsonProcess(String request) {
        String jsonTxt = getJson();
        JSONObject json = new JSONObject(jsonTxt);
        JSONObject json1 = new JSONObject(request);
        JSONArray jsonArray = json.getJSONArray("DcmRequest");
        JSONObject jsonInside = (JSONObject) jsonArray.get(0);
        Iterator<String> keys = jsonInside.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals("DcmLogin")) {
                JSONObject dcmLogin = jsonInside.getJSONObject(key);
                dcmLogin.put("userId", json1.optString("LMUserId"));
            }
            if (jsonInside.get(key) instanceof JSONObject || jsonInside.get(key).toString().contains("<configuration>")) {
                // do something with jsonObject here
            } else {
                if (jsonInside.get(key).toString().contains(",")) {
                    String[] elements = jsonInside.get(key).toString().split(",");
                    StringBuilder keyValue = new StringBuilder();
                    for (int i = 0; i < elements.length - 1; i++) {
                        keyValue.append(json1.get(elements[i]));
                    }
                    jsonInside.put(key, keyValue.toString());
                } else {
                    jsonInside.put(key, json1.get(String.valueOf(jsonInside.get(key))));
                }
            }
        }
        return json.toString();
    }

    public String getJson() {
        String jsonString = Helper.ClientResponse(appProperties.vendorUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readValue(jsonString, JsonNode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        JsonNode dataNode = rootNode.get("data");
        JsonNode requestNode = null;
        for (JsonNode node : dataNode) {
            if (node.get("requestType").asText().equals("transactionjsonhellopaisa")) {
                requestNode = node.get("DcmRequest");
                break;
            }
        }
        if (requestNode == null) {
            requestNode = objectMapper.createArrayNode();
        }
        ObjectNode resultNode = objectMapper.createObjectNode();
        resultNode.set("DcmRequest", requestNode);
        return resultNode.toString();
    }
}

