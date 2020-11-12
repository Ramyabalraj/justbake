package com.kgisl.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class CategoryNotFound extends Exception {

	public CategoryNotFound(int id) {
		// TODO Auto-generated constructor stub
		
		super("CategoryNotFoundException with id="+id);
		
	
	}

}
