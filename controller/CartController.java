package com.kgisl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kgisl.dao.CartDAO;
import com.kgisl.model.Cart;


/* @author  Praveena.D Ramya.B
 * @since  
 * 
 */
@Controller
@RequestMapping("/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {
	@Autowired
	CartDAO cartDAO;

	/*
	 * To add a product in a cart using user id and product id
	 */
	@RequestMapping(value = "/{userid}/{prdid}", method = RequestMethod.POST)
	public String add(ModelMap m, @PathVariable int userid, @PathVariable int prdid) {
		List<Cart> listcart = cartDAO.getCart(userid);
		cartDAO.save(userid, prdid);
		return "listcart";
	}
	
	

	/*
	 * show single value
	 */
//@RequestMapping(value="/"  )
//public @ResponseBody Cart getCart() {
//	String message = "Hello World!";
//	List<Cart> listC = cartDAOImpl.ListFromCart();
//	int cartcount=cartDAOImpl.CartCount();
//	
//	return listC;
//return "index"; produces = MediaType.APPLICATION_JSON_VALUE
//	
//}

	
	/*
	 * show all values
	 */
	@RequestMapping(value = "/{userId}")
	public @ResponseBody List<Cart> getCart(@PathVariable int userId) {
		List<Cart> listC = cartDAO.getCart(userId);
		int cartcount = cartDAO.count();
		return listC;
	}
	
	
	
	/*
	 * show delete from cart using user id and product id 
	 */
	@RequestMapping(value = "/{userid}/{cartid}",method = RequestMethod.DELETE)
	public String delete(@PathVariable int userid, @PathVariable int cartid) {
		cartDAO.delete(userid, cartid);
		return "redirect:/all";
	}
}
