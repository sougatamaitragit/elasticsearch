package com.casestudy.searchmetrics.adapterimpl;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.casestudy.searchmetrics.adapter.DataExchangeAdapter;
import com.casestudy.searchmetrics.entity.Currency;
import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.util.GenericServiceConstant;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class is responsible for converting BitCoinExchange Data to SearchOnline Specific Exchange rate
 * Based on each Third Party rate provider there will be separate implementation like this
 * @author Admin
 *
 */
@Configuration
public class BitCoinExchangeDataAdapterImpl implements DataExchangeAdapter{
	/**
	 * This method converts Exchange's return value to Existing ExchangeRate Object
	 * @param actualObj
	 * @return
	 */
	public ExchangeRate covertToExchangeRate(JsonNode actualObj) {
		ExchangeRate exchangeRate = new ExchangeRate();
		double lastPrice = actualObj.get(GenericServiceConstant.LAST_PRICE_NODE_NAME).asDouble();
		long timestampinSec = actualObj.get(GenericServiceConstant.TIME_STAMP).asLong();
		String currencyDisplayValue = actualObj.get(GenericServiceConstant.DISPLAY_SYMBOL).asText();
		String symbol[] = currencyDisplayValue.split(GenericServiceConstant.SPLITTER);
		String bitCoinSymbol = symbol[0];
		String usdSymbol = symbol[1];
		Currency fromCurrency = new Currency();
		fromCurrency.setCurrencyCode(bitCoinSymbol);
		Currency toCurrency = new Currency();
		toCurrency.setCurrencyCode(usdSymbol);
		exchangeRate.setFromCurrency(fromCurrency);
		exchangeRate.setToCurrency(toCurrency);
		exchangeRate.setExchangeRate(lastPrice);
		exchangeRate.setTimestamp(new Date(timestampinSec*1000l));
		
		return exchangeRate;
	}


}
