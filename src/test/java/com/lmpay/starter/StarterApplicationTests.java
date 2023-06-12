package com.lmpay.starter;

import com.lmpay.starter.model.GenericRequest;
import com.lmpay.starter.processor.FederalBankProcessor;
import com.lmpay.starter.processor.HelloPaisaProcessor;
import io.github.jamsesso.jsonlogic.JsonLogicException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StarterApplicationTests {
	@Autowired
	private FederalBankProcessor federalBankProcessor;
	@Autowired
	private HelloPaisaProcessor helloPaisaProcessor;

//	@Test
//	void contextLoads() {
//		String res = federalBankProcessor.getJson("transactionjsonfederalintra");
//		System.out.println("res >. " + res);
//	}

	@Test
	public void confirm() throws JsonLogicException {
		GenericRequest request = new GenericRequest();
		request.setLMTransactionDate("15-03-2023");
		request.setLMTransactionReferenceNumber("1307049");
		request.setLMSenderFirstName("ACME Corporation");
		request.setLMSenderAccountNumber("14370100109751");
		request.setLMSenderAddress("Federal Bank pvt ltd Aluva Kerala");
		request.setLMSenderAddressCountry("Dubai");
		request.setLMSenderBankName("UiExchange");
		request.setLMSwiftCode("2");
		request.setLMReceiverFirstName("ACME Corporation");
		request.setLMReceiverAccountNumber("99980100068100");
		request.setLMReceiverAddress("Federal Bank pvt ltd Aluva Kerala");
		request.setLMReceiverIdentificationNumber("FDRL0001001");
		request.setLMAmount("300000");
		request.setLMSendCurrency("INR");
		request.setLMSendPurposeOfTransfer("for paying bill");
		request.setLMPurposeCode("P0101");
		request.setLMAlternativePayment("");

		String res = federalBankProcessor.bookTransaction(request);
		System.out.println("res >> " + res);
	}

	@Test
	public void getJsonResPostConfirm() throws IOException {
		GenericRequest request = new GenericRequest();
		request.setLMDeliveryOption("COTC");
		request.setLMTransactionReferenceNumber("138051829248");
		request.setLMReceiveCurrency("BWP");
		request.setLMAmount("10");
		request.setLMSendPurposeOfTransfer("Family");
		request.setLMReceiverFirstName("ABHY");
		request.setLMReceiverLastName("DRAVID");
		request.setLMReceiverMobileWalletNumber("2323232323");
		request.setLMReceiverNationality("BWA");
		request.setLMReceiverAccountNumber("1976010126");
		request.setLMReceiverBankIdentificationCode("");
		request.setLMReceiverBranchName("Capital Bank Botswana");
		request.setLMReceiverAddress("Botswana");
		request.setLMSenderFirstName("Mickey");
		request.setLMSenderLastName("Mouse");
		request.setLMSenderAddressCountry("UAE");
		request.setLMSenderMobileNumber("971501700923");
		request.setLMSenderAddress("Dubai");
		request.setLMSenderState("Dubai");
		request.setLMSenderNationality("AE");
		request.setLMSenderCity("Dubai");
		request.setLMSenderPostalCode("tH4 5BX");
		request.setLMSendCurrency("AED");
		request.setLMSenderGender("M");
		request.setLMSenderIDNumber("930706890798");
		request.setLMSenderDOB("1990-01-08");
		request.setLMSenderIDExpiryDate("2022-10-10");
		request.setLMSenderIDIssueDate("1995-05-05");
		request.setLMSenderIDType("53");
		request.setLMSenderIDIssuanceCountry("AE");
		request.setLMDestinationCountry("BW");
		request.setLMReceiverBankSubCode("CBBP");

		String res = helloPaisaProcessor.confirmTransaction(request);
		System.out.println("res >> " + res);
	}

}
