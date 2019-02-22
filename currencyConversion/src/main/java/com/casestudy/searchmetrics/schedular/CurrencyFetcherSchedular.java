package com.casestudy.searchmetrics.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.service.AbstractExchangeRateGateway;
/**
 * This class defines a scheduler based ona given Cron expression .
 * @author Admin
 * @version 1.0
 *
 */
@Component
public class CurrencyFetcherSchedular {
	
	@Autowired
	AbstractExchangeRateGateway exchangerategateway;
	
	/**
	 * This the scheduled method which is called based on given schedule.
	 * This method call thirdPartyAPI Gateway to get exchange rate.
	 */
	
	@Scheduled(cron = "${cron.schedular.expression}")
	//@Scheduled(fixedDelay=50000)
	public void scheduleFixedRateTask() {
		
		exchangerategateway.getExhangeRates();
		
		
	}

}
