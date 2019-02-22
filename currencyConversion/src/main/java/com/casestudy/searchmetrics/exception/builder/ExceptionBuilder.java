package com.casestudy.searchmetrics.exception.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.exception.ResourceNotFoundException;
import com.casestudy.searchmetrics.util.GenericServiceConstant;
/**
 * This method builds exception message for different business scenarios.
 * @author Admin
 * @version 1.0
 * 
 */
@Component
public class ExceptionBuilder {
	
	@Autowired
	ErrorMessagesReader messageReader;
    /**
     * This method is responsible for building Exception which has to be thrown when there is no resource found for an identifier.
     * @param id Id of the Resource 
     * @return
     */
	public ResourceNotFoundException noRateFoundFoundExceptionBuilder(String args []) {
		ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
		String errorMessage = messageReader.get(GenericServiceConstant.NO_RATES_FOUND_ERRM_KEY, args);
		String errorCode = messageReader.get(GenericServiceConstant.NO_RATES_FOUND_ERR_CODE_KEY, null);
		resourceNotFoundException.setErrorCode(errorCode);
		resourceNotFoundException.setErrorMessage(errorMessage);
		return resourceNotFoundException;

	}
	 
	public ResourceNotFoundException noCurrencyCodeFound(String args []) {
		ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
		String errorMessage = messageReader.get(GenericServiceConstant.NO_CODE_FOUND_ERR_CODE_KEY, args);
		String errorCode = messageReader.get(GenericServiceConstant.NO_CODE_FOUND_ERRM_KEY, null);
		resourceNotFoundException.setErrorCode(errorCode);
		resourceNotFoundException.setErrorMessage(errorMessage);
		return resourceNotFoundException;

	}

}
