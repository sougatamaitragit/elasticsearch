package com.casestudy.searchmetrics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.entity.ExchangeRateHistory;
/**
 * This is an utility class and used accorss all layers
 * @author Admin
 * @version 1.0
 */
@Component
public class DataPopulatorUtil {

	/*
	 * This method create a ExchangeRateHistory object from ExchangeRate object
	 */ 
	
	public  ExchangeRateHistory prepareExchangeHistory(ExchangeRate er) {
		ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
		exchangeRateHistory.setInsertTimestamp(String.valueOf(System.currentTimeMillis()));
		exchangeRateHistory.setExchangeRateId(er.getId());
		exchangeRateHistory.setExchangeRateStoreDate(er.getTimestamp().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(GenericServiceConstant.DISPLAY_DATE_FORMAT);
		Date lastStoredDate = er.getTimestamp();
		String displaydate = sdf.format(lastStoredDate);
		exchangeRateHistory.setFromCurrencyCode(er.getFromCurrency().getCurrencyCode());
		exchangeRateHistory.setToCurrencyCode(er.getToCurrency().getCurrencyCode());
		exchangeRateHistory.setDisplayDate(displaydate);
		exchangeRateHistory.setFromCurrencyName(er.getFromCurrency().getCurrencyName());
		exchangeRateHistory.setToCurrencyName(er.getToCurrency().getCurrencyName());
		exchangeRateHistory.setExchangeRate(er.getExchangeRate());
		exchangeRateHistory.setVersionNo(er.getVersionNo());
		return exchangeRateHistory;
	}
	
	
}
