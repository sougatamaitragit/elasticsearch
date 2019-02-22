package com.casestudy.searchmetrics.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casestudy.searchmetrics.elasticsearch.repositry.ExchangeRateHistoryRespository;
import com.casestudy.searchmetrics.entity.Currency;
import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.entity.ExchangeRateHistory;
import com.casestudy.searchmetrics.exception.builder.ExceptionBuilder;
import com.casestudy.searchmetrics.repositry.CurrencyReposiroty;
import com.casestudy.searchmetrics.repositry.ExchangeRateRepository;
import com.casestudy.searchmetrics.service.ExchangeRateService;
import com.casestudy.searchmetrics.util.DataPopulatorUtil;
/**
 * This class defines 3 methods 
 * 1. storeRates - This method stores new rate by updating existing record and also it keeps a history of all all records in elasticsearch .
 * 2. getLatestRate - This method retrieves current conversion rate of currency among two given currencies
 * 3. getHistoricRates - This method retrieves currency conversion rate history
 * @author Admin
 *
 */
@Service
public class ExchangeRatesServiceImpl implements ExchangeRateService{

	@Autowired
	ExchangeRateRepository exchangeRateRepo;
	
	@Autowired 
	CurrencyReposiroty currencyRepo;
	
	@Autowired
	ExchangeRateHistoryRespository exchangerateHistory; 
	
	@Autowired
	ExceptionBuilder exceptionBuilder;
	
	@Autowired
	ExchangeRateCacheService exchangeRateCache;
	
	@Autowired
	DataPopulatorUtil dataPopulator;
	/*
	 * This method stores current currency exchange rate and keep a history of records. Once record is stored it updates cache with Stored one
	 * 
	 * @see com.casestudy.searchmetrics.service.ExchangeRateService#storeRates(com.casestudy.searchmetrics.entity.ExchangeRate)
	 */
	@Override
	@Transactional
	@CachePut(value="currentrates" ,key="{#exchangeRate.fromCurrency.currencyCode+#exchangeRate.toCurrency.currencyCode}")
	 
	public ExchangeRate storeRates(ExchangeRate exchangeRate) {
		ExchangeRateHistory exRateHistory = null;
		Currency fromCurrency = currencyRepo.findByCurrencyCode(exchangeRate.getFromCurrency().getCurrencyCode());
		Currency toCurrency   = currencyRepo.findByCurrencyCode(exchangeRate.getToCurrency().getCurrencyCode());
		if(fromCurrency ==null ) {
			String args[]= new String[1];
			args[0] = exchangeRate.getFromCurrency().getCurrencyCode();
				throw exceptionBuilder.noCurrencyCodeFound(args);
		} 
		if(toCurrency == null) { 
			String args[]= new String[1];
			args[0] = exchangeRate.getFromCurrency().getCurrencyCode();
			throw exceptionBuilder.noCurrencyCodeFound(args);
		}
		
		ExchangeRate currentExchangeRate = exchangeRateRepo.findByFromCurrencyAndToCurrency
					(exchangeRate.getFromCurrency().getCurrencyCode(), exchangeRate.getToCurrency().getCurrencyCode());
		
		exchangeRate.setFromCurrency(fromCurrency);
		exchangeRate.setToCurrency(toCurrency); 
		if(currentExchangeRate!=null) {
			exchangeRate.setId(currentExchangeRate.getId());
			exchangeRate.setVersionNo(currentExchangeRate.getVersionNo());
		}
		if(currentExchangeRate!=null) {
			// Store History in Elastic Search 
			 exRateHistory = dataPopulator.prepareExchangeHistory(currentExchangeRate);
		}
		exchangeRate = exchangeRateRepo.save(exchangeRate);
		if(currentExchangeRate!=null) {
			exchangerateHistory.save(exRateHistory);
		}
		 return exchangeRate;
	}
	/*
	 * This method fetches current currency conversion rate from cache. If no data exists in cache then throws exception.
	 * 
	 * @see com.casestudy.searchmetrics.service.ExchangeRateService#getLatestRate(java.lang.String, java.lang.String)
	 */
	@Override 
	@Transactional()
	public ExchangeRate getLatestRate(String fromCurrency,String toCurrency){
		ExchangeRate exchangeRate =  exchangeRateCache.getFromCache(fromCurrency, toCurrency);
		// If No data exists in cache re-load it from DB 
		if(exchangeRate==null) {
			exchangeRate = exchangeRateRepo.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
		}
		
		if(exchangeRate==null) {
			String args[] = new String[2];
			args[0] = fromCurrency;
			args[1] = toCurrency; 
			throw exceptionBuilder.noRateFoundFoundExceptionBuilder(args);
		}
		return exchangeRate;
	}
	 
	
	/* This method retrieves currency history from elastic search repository by given from and to currency and a date
	 * 
	 * @see com.casestudy.searchmetrics.service.ExchangeRateService#getHistoricRates(java.lang.String, java.lang.String, java.util.Date, org.springframework.data.domain.Pageable)
	 */
	@Transactional
	public Page<ExchangeRateHistory> getHistoricRates(String fromCurrency,String toCurrency ,Date fromdate,Date todate,Pageable pageable){
		long frmDT = fromdate.getTime();
		long toDT  = todate.getTime(); 
		return exchangerateHistory. 
				getAllByFromCurrencyCodeAndToCurrencyCodeAndExchangeRateStoreDateBetween(fromCurrency,toCurrency,frmDT,toDT,pageable);
	}
	
	
	
	
}
