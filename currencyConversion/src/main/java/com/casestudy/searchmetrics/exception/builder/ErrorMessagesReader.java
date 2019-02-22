package com.casestudy.searchmetrics.exception.builder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
/**
 * This is an utility to load Message Resources and returning message after converting placeholders with dynamic
 * values passed from called.
 * @author Admin
 * @version 1.0
 */
@Component
public class ErrorMessagesReader {
	@Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor; 

    @PostConstruct
    private void init() {
    	
        accessor = new MessageSourceAccessor(messageSource);
    }
    /**
     * This method replaces place holders from a message by given arguments.
     * First placeholder will be replaced by 0 th element of the array and so on.
     * @param code
     * @param args
     * @return 
     */
    public String get(String code,Object[] args) {
    	String message =  accessor.getMessage(code, args);
    	return message;
    }
}
