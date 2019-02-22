package com.casestudy.searchmetrics;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.casestudy.searchmetrics.elasticsearch.repositry.ExchangeRateHistoryRespository;
import com.casestudy.searchmetrics.entity.Currency;
import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.entity.ExchangeRateHistory;
import com.casestudy.searchmetrics.exception.builder.ExceptionBuilder;
import com.casestudy.searchmetrics.repositry.CurrencyReposiroty;
import com.casestudy.searchmetrics.repositry.ExchangeRateRepository;
import com.casestudy.searchmetrics.service.impl.ExchangeRateCacheService;
import com.casestudy.searchmetrics.service.impl.ExchangeRatesServiceImpl;
import com.casestudy.searchmetrics.util.DataPopulatorUtil;



@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceImplTest { 

	@InjectMocks
	private ExchangeRatesServiceImpl targetService;

	@Mock
	private ExchangeRateRepository repository;
	
	@Mock
	private CurrencyReposiroty currencyRepository;
	
	@Mock
	ExchangeRateHistoryRespository exchangeRateHistoryRepo;
	
	@Mock
	ExceptionBuilder mockExceptionBuilder;
	
	
	@Mock
	ExchangeRateCacheService exchangerateCache;
	
	@Mock
	DataPopulatorUtil dataPopulatorUtil;
	
	@Test
	public void test_getLatestRate() {
		String fromCurrencyCode ="BTC";
		String toCurrencyCode ="USD";
		ExchangeRate exchangeRate = new ExchangeRate();
		when(exchangerateCache.getFromCache(fromCurrencyCode, toCurrencyCode)).thenReturn(exchangeRate);
		assertNotNull(exchangeRate);
		targetService.getLatestRate(fromCurrencyCode, toCurrencyCode);
		
	} 
	 
	
	
	@Test
	public void test_getHistoricRates() {
		String fromCurrencyCode ="BTC";
		String toCurrencyCode ="USD";
		Date fromDate = new Date(); 
		Date toDate = new Date();
		Pageable pageable = PageRequest.of(1, 1);
		targetService.getHistoricRates(fromCurrencyCode, toCurrencyCode, fromDate, toDate, pageable);
		
		verify(exchangeRateHistoryRepo).getAllByFromCurrencyCodeAndToCurrencyCodeAndExchangeRateStoreDateBetween(fromCurrencyCode, toCurrencyCode, fromDate.getTime(), toDate.getTime(), pageable);
	}
	 
	@Test
	public void test_StoreRates() {
		/******************* CREATING INPUT **********************/
		String fromCurrencyCode ="BTC";
		String toCurrencyCode ="USD"; 
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setTimestamp(new Date());
		exchangeRate.setExchangeRate(100D);
		Currency fromcurrency = new Currency();
		fromcurrency.setCurrencyCode(fromCurrencyCode);
		Currency tocurrency = new Currency();
		tocurrency.setCurrencyCode(toCurrencyCode); 
		exchangeRate.setFromCurrency(fromcurrency);
		exchangeRate.setToCurrency(tocurrency);
		/************ INPUT CREATION END *******************************/
		Currency storedFromCurr = new Currency();
		Currency storedToCurr = new Currency();
		storedFromCurr.setCurrencyCode(fromCurrencyCode);
		storedToCurr.setCurrencyCode(toCurrencyCode); 
		
		ExchangeRate storedRate = new ExchangeRate();
		storedRate.setId(1l);
		when(currencyRepository.findByCurrencyCode(exchangeRate.getFromCurrency().getCurrencyCode())).thenReturn(storedFromCurr);
		when(currencyRepository.findByCurrencyCode(exchangeRate.getToCurrency().getCurrencyCode())).thenReturn(storedToCurr);
		when(repository.findByFromCurrencyAndToCurrency(fromCurrencyCode, toCurrencyCode)).thenReturn(storedRate);
		
		ExchangeRateHistory history =mock( ExchangeRateHistory.class);
		when(dataPopulatorUtil.prepareExchangeHistory(storedRate)).thenReturn(history);
		targetService.storeRates(exchangeRate);
		verify(exchangeRateHistoryRepo).save(history);
	}
	
	
	 
	


	
	
}
