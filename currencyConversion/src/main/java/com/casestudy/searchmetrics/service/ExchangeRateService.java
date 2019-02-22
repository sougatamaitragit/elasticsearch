package com.casestudy.searchmetrics.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.entity.ExchangeRateHistory;
/**
 * This interface defines contracts for 1. storing Rates. 2. Get Latest Exchange rate 3. Get Historic Rates
 * @author Admin
 *
 */
public interface ExchangeRateService {
	
	public ExchangeRate storeRates(ExchangeRate exchangeRate);
	
	public ExchangeRate getLatestRate(String fromCurrency,String toCurrency);
	
	public Page<ExchangeRateHistory> getHistoricRates(String fromCurrency,String toCurrency ,Date fromdate,Date todate,Pageable pageable);
	

}
