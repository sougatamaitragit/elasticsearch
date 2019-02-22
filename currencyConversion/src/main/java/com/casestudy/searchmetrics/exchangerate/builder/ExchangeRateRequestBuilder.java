package com.casestudy.searchmetrics.exchangerate.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Build Exchange Rate request
 * @author Admin
 * @version 1.0
 */
@Configuration
public class ExchangeRateRequestBuilder {
	
	@Autowired
	ExchangeRateSignatureBuilder exhangerateSignatureBuilder;
	
	public HttpEntity<String> getRequestEntity() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = null;
		try {
		headers.add("X-Signature", exhangerateSignatureBuilder.getSignature());
		 entity = new HttpEntity<>(headers);
		 }catch(Exception e) {
			 throw new RuntimeException(e.getMessage());
		 }
		return entity;
	}

}
