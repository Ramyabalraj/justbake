package com.kgisl.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class GlobalException {

	public GlobalException() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(ModelMap m,HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		m.addAttribute("sqlexception", "SQLException Occured");
		return "db";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException Occured");
		//returning 404 error code
	}
	//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="NullPointerException occured")
//	@ExceptionHandler(NullPointerException.class)
//	public String handleNullPointerException(ModelMap m,HttpServletRequest request,Exception ex){
//		
//		logger.info("NullPointerException Occured:: URL="+request.getRequestURL());
//		m.addAttribute("NullPointerException", "NullPointerException Occured");
//		return "db";
//	}
	@ExceptionHandler(ProductNotFound.class)
	public ModelAndView handleProductNotFoundException(HttpServletRequest request, Exception ex) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("error");
		return modelAndView;
	}
}
