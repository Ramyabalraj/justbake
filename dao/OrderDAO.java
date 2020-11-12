package com.kgisl.dao;

import java.util.List;

import com.kgisl.model.Category;
import com.kgisl.model.Order;

public interface OrderDAO {
	public List<Order> order(Order order);

	public List<Order> cartOrder(Order order);

	public List<Order> get(int userId);

}
