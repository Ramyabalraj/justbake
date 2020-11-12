package com.kgisl.dao;

import java.util.List;

import com.kgisl.model.Product;

public interface ProductDAO {

	public List<Product> getAllProduct();

	public List<Product> getProduct(int productId);

	public void create(Product p) throws Exception;

	public void update(Product p);

	public void delete(int productId);

	public List<Product> selectProduct(String catname);

}
