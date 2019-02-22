package com.casestudy.searchmetrics.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.casestudy.searchmetrics.api.error.model.ApiError;
import com.casestudy.searchmetrics.exception.ResourceNotFoundException;
import com.casestudy.searchmetrics.util.GenericServiceConstant;


@ControllerAdvice
public class CurrencyServiceControllerAdvice {
	
	/**
	 * This method handles ResourceNotFoundException  and send HTTP 404 error code 
	 * @param ex
	 * @return
	 */
	
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiError res = new ApiError();
		res.setExceptionCode(ex.getErrorCode());
		res.setErrorMessage(ex.getErrorMessage());
		return res;
	}
	
	
	
	/**
	 * This method handles Exception  and send HTTP 500 error code 
	 * @param ex Exception
	 * @return ApiError
	 */
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError genericException(Exception ex) {
		ApiError res = new ApiError();
		res.setExceptionCode(GenericServiceConstant.GENERIC_ERROR_CODE);
		res.setErrorMessage(ex.getMessage());
		ex.printStackTrace();
		return res;
	}
	
}
