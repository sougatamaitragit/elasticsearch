package com.casestudy.searchmetrics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="EXCHANGE_RATE_TAB")

public class ExchangeRate {
	
	@ApiModelProperty(notes = "Identify of Exchange_Rate_Tab")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	
	@ApiModelProperty(notes = "From currency Id")
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="FROM_ID")
    Currency fromCurrency;
	
	@ApiModelProperty(notes = "To currency Id")
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TO_ID")
	Currency toCurrency;
	
	@ApiModelProperty(notes = "Last Rate  Timestamp")
	@Column(name="LAST_RECORD_TIME")
	Date timestamp;
	
	@ApiModelProperty(notes = "Last Exchange Rate")
	@Column(name="EXCHANGE_RATE")
	Double exchangeRate;
	
	@Version
	long versionNo;
	
	
	public long getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Currency getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(Currency fromCurrncy) {
		this.fromCurrency = fromCurrncy;
	}
	public Currency getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(Currency toCurrency) {
		this.toCurrency = toCurrency;
	}
	
	public Double getExchangeRate() {
		return exchangeRate;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
