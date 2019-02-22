package com.casestudy.searchmetrics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.casestudy.searchmetrics.adapter.DataExchangeAdapter;
import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.exchangerate.builder.ExchangeRateRequestBuilder;
import com.casestudy.searchmetrics.service.AbstractExchangeRateGateway;
import com.casestudy.searchmetrics.service.ExchangeRateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class calls BitCoin Gateway to fetch bit coin exchange rates
 * 
 * @author Admin
 * @version 1.0
 */
@Component
public class BitCoinExchangeRateAPIGateway implements AbstractExchangeRateGateway {

	@Value("${exchangerate.api.url}")
	String excahngeRateUrl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ExchangeRateRequestBuilder requestBuilder;

	@Autowired
	ExchangeRateService exchangeRateService;
	
	@Autowired
	ExchangeRateCacheService cacheService;
	
	@Autowired
	DataExchangeAdapter dataExchangeAdapter;
	

	/**
	 * This class Get Exchange rate by calling a third party API . Once exchange
	 * rate is available this method call's service to store exchange rate data
	 */
	public void getExhangeRates() {

		ResponseEntity<String> responseEntity = restTemplate.exchange(excahngeRateUrl, HttpMethod.GET,
				requestBuilder.getRequestEntity(), String.class);
		if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
			JsonNode actualObj = getJsonNode(responseEntity);
			ExchangeRate exchangeRate = dataExchangeAdapter.covertToExchangeRate(actualObj);
			ExchangeRate oldExchangeRate = cacheService.getFromCache(exchangeRate.getFromCurrency().getCurrencyCode(),
											exchangeRate.getToCurrency().getCurrencyCode());
			// Store only if there is a change in rate
			if(oldExchangeRate==null || (oldExchangeRate.getExchangeRate().doubleValue() != exchangeRate.getExchangeRate().doubleValue() ))
			{
				exchangeRateService.storeRates(exchangeRate);
			}
		}

	}

	private JsonNode getJsonNode(ResponseEntity<String> responseEntity) {
		JsonNode actualObj = null;
		try {
			String response = responseEntity.getBody();
			System.out.println(response);
			ObjectMapper mapper = new ObjectMapper();
			actualObj = mapper.readTree(response);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return actualObj;
	}

	
}
