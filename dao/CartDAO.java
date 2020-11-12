package com.kgisl.dao;

import java.util.List;

import com.kgisl.model.Cart;

public interface CartDAO {

	public List<Cart> getCart(int userId);

	public void delete(int userId, int prdId);

	public void save(int userId, int prdId);

	public int count();

}
