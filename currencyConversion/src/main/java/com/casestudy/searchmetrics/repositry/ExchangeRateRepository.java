package com.casestudy.searchmetrics.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.searchmetrics.entity.ExchangeRate;
/**
 * This repository defines contact for Getting current Exchange rate
 * @author Admin
 *
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>{
	@Query("Select a from ExchangeRate a  WHERE  a.fromCurrency.currencyCode = ?1 AND a.toCurrency.currencyCode = ?2 ")
	public ExchangeRate findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
	

}
