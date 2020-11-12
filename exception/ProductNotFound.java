package com.kgisl.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class ProductNotFound extends Exception {

	public ProductNotFound(int prdid) {
		// TODO Auto-generated constructor stub
		super("ProductNotFoundException with id="+prdid);
		
	}
	

}
