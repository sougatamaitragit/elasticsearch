package com.casestudy.searchmetrics.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.casestudy.searchmetrics.entity.ExchangeRateHistory;
import com.casestudy.searchmetrics.service.ExchangeRateService;
import com.casestudy.searchmetrics.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * This class is responsible for exposing public API's for 
 * 1. Getting Current exchange rate for given from and to currency 
 * 2. Getting historic exchange rate for given from and to currency and between two given dates.
 * @author Admin
 * @version 1.0
 */
@Api(value="This service exposes endpoints for getting current currency exchange rate")
@RestController
public class RatesController { 
	
	  
	@Autowired
	ExchangeRateService exchangeRate;
	
	@ApiOperation(value = "Retrievs current currency conversion rate between two currency codes ", response = ExchangeRate.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	})
	
	@GetMapping(value="/exchangerates/exchangerate",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRate getCurrentConversionRate (@RequestParam ("fromcurrency") String fromCurrency,@RequestParam("tocurrency") String toCurrency) {
		return exchangeRate.getLatestRate(fromCurrency, toCurrency);
	}
	
	
	
	@ApiOperation(value = "Retrieves  currency conversion rate between two currency codes between a given start and end date. ", response = ExchangeRate.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	})
	
	@GetMapping(value="/exchangerates/exchangeratehistory",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Page<ExchangeRateHistory> getConevrsionHistory (@RequestParam("fromcurrency") String fromCurrency,
														   @RequestParam("tocurrency") String toCurrency,
			 											   @RequestParam ("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromdate,
														   @RequestParam ("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date todate,
										 				   @RequestParam ("page") int page, 
														   @RequestParam ("size") int size ) {
		// As todate is given in format of yyyy-MM-dd , it will always take time upto 00:00:000 so augmenting time till day end i.e 23.59.59
		//
		Date modifiedTodate = DateUtil.appendHourMinuteSecondTillDayEnd(todate);
		Pageable pagingRequest = PageRequest.of(page, size,Sort.by("versionNo"));
		return exchangeRate.getHistoricRates(fromCurrency, toCurrency, fromdate, modifiedTodate, pagingRequest);
				
	}
	
	
	
	
	
}
