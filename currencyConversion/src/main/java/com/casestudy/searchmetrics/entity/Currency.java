package com.casestudy.searchmetrics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Currency {
	@ApiModelProperty(notes = "Identify of Currency")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@ApiModelProperty(notes = "Full name of the Company")
	@Column(name="CURRENCY_NAME")
	String currencyName;
	@ApiModelProperty(notes = "Currency Code")
	@Column(name="CURRENCY_CODE")
	String currencyCode;
	
	public Long getId() { 
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

}
