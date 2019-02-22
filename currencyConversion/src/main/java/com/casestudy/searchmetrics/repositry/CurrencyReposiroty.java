package com.casestudy.searchmetrics.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.entity.Currency;
/**
 * This is the repository class for Currency 
 * @author Admin
 * @version 1.0
 */
@Component
public interface CurrencyReposiroty extends JpaRepository<Currency, Long>{
	/**
	 * This interface defines contract for getting Currency by currency code
	 * @param currencyCode
	 * @return
	 */
	public Currency findByCurrencyCode(String currencyCode);
	

}
