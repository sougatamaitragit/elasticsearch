package com.casestudy.searchmetrics.exception;
/**
 * This is custom exception class. This is a runtime expression which is thrown if No suitable
 * resource is found
 * @author Admin
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715236754867056689L;
	String errorMessage;
	String errorCode; 
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
