package com.casestudy.searchmetrics.adapter;

import com.casestudy.searchmetrics.entity.ExchangeRate;
import com.fasterxml.jackson.databind.JsonNode;

public interface DataExchangeAdapter {
	
	
	public ExchangeRate covertToExchangeRate(JsonNode jsonNode) ;

}
