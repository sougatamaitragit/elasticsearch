package com.casestudy.searchmetrics.service;

/**
 * This interface contract for getting exchange rate. Based on each provider , implementations has to be defined 
 * @author Admin
 * @version 1.0
 *
 */

public interface AbstractExchangeRateGateway {
	public void getExhangeRates();
}
