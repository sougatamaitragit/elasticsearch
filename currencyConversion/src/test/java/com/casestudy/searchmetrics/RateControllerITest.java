package com.casestudy.searchmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.searchmetrics.entity.ExchangeRate;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RateControllerITest {
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	} 
	@LocalServerPort
	private int port;
	HttpHeaders headers = new HttpHeaders();

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	
	@Test 
	public void getCurrentRate() {
		HttpEntity<ExchangeRate> entity = new HttpEntity<ExchangeRate>(headers);
		ResponseEntity<ExchangeRate> response = restTemplate.exchange(createURLWithPort("/exchangerates/exchangerate?fromcurrency=abc&tocurrency=pqr"),
				HttpMethod.GET, entity, ExchangeRate.class);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
	}

}  
