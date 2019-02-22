package com.casestudy.searchmetrics.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.entity.ExchangeRate;
/**
 * This is the cache service for getting Current Exchange rate from cache
 * @author Admin
 *
 */
@Component
public class ExchangeRateCacheService {
	/**
	 * This method returns current Exchange Rate From cache . If no Exchange rate available
	 * in cache then retruns null.
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Cacheable(value ="currentrates",key="{#fromCurrency+#toCurrency}")
	public ExchangeRate getFromCache(String fromCurrency,String toCurrency) {
		return null;
	}
	
}
