package com.kgisl.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kgisl.model.Category;
@Component
public interface CategoryDAO {

	public List<Category> getAllCategory();

	public List<Category> getCategory(int catId);

	public void delete(int catId);

	public void update(Category c) throws Exception;

	public void save(Category c) throws Exception;
}
