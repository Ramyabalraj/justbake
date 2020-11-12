package com.kgisl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.dao.CategoryDAO;
import com.kgisl.model.Category;


public class CategoryDAOImpl implements CategoryDAO {

	JdbcTemplate template;

	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Category> getAllCategory() {

		String sql = "SELECT catid,catname,categoryDescription,categoryDesc FROM categories";
		List<Category> listContact = template.query(sql, new RowMapper<Category>() {
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category aContact = new Category();
				aContact.setCatId(rs.getInt("catid"));
				aContact.setCatName(rs.getString("catname"));
				aContact.setCategoryDescription(rs.getString("categoryDescription"));
				aContact.setCategoryDesc(rs.getString("categoryDesc"));

				return aContact;
			}

		});

		return listContact;
	}

	public List<Category> getCategory(int catId) {
		int Catid = catId;
		System.out.println("catid" + Catid);
		String sql = "SELECT * FROM categories where catid='" + Catid + "'";
		List<Category> listC = template.query(sql, new RowMapper<Category>() {
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category a = new Category();
				a.setCatId(rs.getInt("catid"));
				a.setCatName(rs.getString("catname"));

				return a;
			}

		});

		return listC;

	}

	public void delete(int catId) {
		String sql = "DELETE FROM categories WHERE catid=?";
		template.update(sql, catId);
	}

	public void save(Category c) throws Exception {
		System.out.println(c.getCatName());
		String name = c.getCatName();
		if (c.getCatName() == "") {
			System.out.println("dasrh");

			throw new NullPointerException(name);
		} else {
			String sql = "INSERT INTO categories (catname,categoryDescription) values(?,?)";
			template.update(sql, c.getCatName(), c.getCategoryDescription());

		}
	}

	public void update(Category c) throws Exception {
		String name = c.getCatName();
		if (c.getCatName() == "") {
			throw new NullPointerException(name);
		}

		else {
			String sql = "Update categories set catname = ? where catid = ?";
			template.update(sql, c.getCatName(), c.getCatId());
		}
	}

	

	

}
