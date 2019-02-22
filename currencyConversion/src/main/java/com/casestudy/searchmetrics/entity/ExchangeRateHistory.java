package com.casestudy.searchmetrics.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(indexName = "rate", type = "ratehist")
public class ExchangeRateHistory {
	
	@ApiModelProperty(notes = "Rate History Id stored in Search Engine")
	@Id 
	String insertTimestamp;
	@ApiModelProperty(notes = "Exchange rate Id")
	Long exchangeRateId;
	@ApiModelProperty(notes = "Exchange Rate at ")
	Long exchangeRateStoreDate;
	@ApiModelProperty(notes = "Exchange Rate display date ")
	String displayDate;
	@ApiModelProperty(notes = "From Currency Code ")
	String fromCurrencyCode ;
	@ApiModelProperty(notes = "To Currency Code ")
	String toCurrencyCode;
	@ApiModelProperty(notes = "From Currency Name ")
	String fromCurrencyName;
	@ApiModelProperty(notes = "To Currency Name ")
	String toCurrencyName;
	@ApiModelProperty(notes = "Exchange Rate ")
	Double exchangeRate;
	@ApiModelProperty(notes = "Verison ")
	Long versionNo;
	
	
	public Long getExchangeRateId() {
		return exchangeRateId;
	}
	public void setExchangeRateId(Long exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}
	
	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}
	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}
	public String getFromCurrencyName() {
		return fromCurrencyName;
	}
	public void setFromCurrencyName(String fromCurrencyName) {
		this.fromCurrencyName = fromCurrencyName;
	}
	public String getToCurrencyName() {
		return toCurrencyName;
	}
	public void setToCurrencyName(String toCurrencyName) {
		this.toCurrencyName = toCurrencyName;
	}
	public String getToCurrencyCode() {
		return toCurrencyCode;
	}
	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}
	public Long getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
	public Long getExchangeRateStoreDate() {
		return exchangeRateStoreDate;
	}
	public void setExchangeRateStoreDate(Long exchangeRateStoreDate) {
		this.exchangeRateStoreDate = exchangeRateStoreDate;
	}
	public String getDisplayDate() {
		return displayDate;
	}
	public void setDisplayDate(String displayDate) {
		this.displayDate = displayDate;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getInsertTimestamp() {
		return insertTimestamp;
	}
	public void setInsertTimestamp(String insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}
	
	
	
	

}
