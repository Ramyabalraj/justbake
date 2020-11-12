package com.kgisl.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kgisl.dao.LoginDAO;
import com.kgisl.model.Category;
import com.kgisl.model.Login;
import com.kgisl.model.Order;




/* @author  Praveena.D Ramya.B
 * @since  
 * 
 */


@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class LoginController {

	@Autowired
	LoginDAO loginDAO;
	
	
	

	/*
	 * Method : get
	 * Description: Returns all logindetails
	 */
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public @ResponseBody List<Login> get(){
		return loginDAO.get();
	}

	
	
	/*
	 * Method : get
	 * Description: Returns all logindetails
	 */
	
	@RequestMapping(value="/{username}/{password}", method = RequestMethod.GET)
	public @ResponseBody List<Login> get(@PathVariable("username") String username,@PathVariable("password") String password){
		return loginDAO.getid(username,password);
	}

	
	
}
