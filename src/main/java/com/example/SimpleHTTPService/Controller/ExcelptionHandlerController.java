package com.example.SimpleHTTPService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExcelptionHandlerController {
	
	//Wrong URL request handling error - 404
		@ExceptionHandler(NoHandlerFoundException.class)
	    @ResponseStatus(value= HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public String requestHandlingNoHandlerFound() {
	        return "WRONG URL REQUEST (404 - NOT FOUND)";
	    }

}
