package com.casestudy.searchmetrics.elasticsearch.repositry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.searchmetrics.entity.ExchangeRateHistory;

/**
 * This is elasticsearch Repository for storing and retrieving ExchangeRateHistory
 * @author Admin
 *
 */
@Repository
public interface ExchangeRateHistoryRespository extends ElasticsearchRepository <ExchangeRateHistory, String>{
	/**
	 * This interface defines contract for getting ExchangeRateHistory by from and to currency 
	 * and from from and to date. This allow paging query . 
	 * @param fromCurrency
	 * @param toCurrency
	 * @param fromdate
	 * @param todate
	 * @param pageable
	 * @return
	 */
	public Page<ExchangeRateHistory> getAllByFromCurrencyCodeAndToCurrencyCodeAndExchangeRateStoreDateBetween
	(String fromCurrency,String toCurrency,long fromdate,long todate,Pageable pageable);
	
}
