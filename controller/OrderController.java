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

import com.kgisl.dao.OrderDAO;
import com.kgisl.dao.impl.OrderDAOImpl;
import com.kgisl.model.Order;




/* @author  Praveena.D Ramya.B
 * @since  
 * 
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	@Autowired
	OrderDAO orderDAO;
	
	
	/*
	 * To Select Products for Order
	 *  Use- User id , Product id
	 */
	@RequestMapping(value = "/cartorder", method = RequestMethod.POST)
	public @ResponseBody List<Order> cart(@RequestBody Order order) throws IOException
	{
		
		List<Order> list = orderDAO.cartOrder(order);
		return list;
	}
	
	
	/*
	 * To Select Products from cart to Order
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody List<Order> order(@RequestBody Order order) 
	{
        List<Order> list = orderDAO.order(order);
		return list;
	}
	
	
	/*
	 * To Select Products for Order
	 *  Use- User id , Product id
	 */
	@RequestMapping(value = "/{id}")
	public @ResponseBody List<Order> get(@PathVariable("id") int id) throws IOException
	{
		
		List<Order> list = orderDAO.get(id);
		return list;
	}
	
}
