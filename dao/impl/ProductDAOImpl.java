package com.kgisl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.dao.ProductDAO;
import com.kgisl.exception.GlobalException;
import com.kgisl.model.Product;


public class ProductDAOImpl implements ProductDAO {
	
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}


	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM products";
		List<Product> listprd = template.query(sql, new RowMapper<Product>() {
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product a = new Product();
				a.setCategoryId(rs.getInt("catid"));
				a.setProductId(rs.getInt("prdid"));
				a.setProductName(rs.getString("prdname"));
				a.setProductPrice(rs.getFloat("prdprice"));
				a.setProductDesc(rs.getString("prddesc"));
				a.setProductImage(rs.getString("prdimg"));
				return a;
			}

		});

		return listprd;
	}


	public List<Product> getProduct(int productId) {
		int ProductId=productId;
		System.out.println(ProductId);
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM products where prdid='"+ProductId+"'";
		List<Product> listprd = template.query(sql, new RowMapper<Product>() {
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product a = new Product();
				a.setCategoryId(rs.getInt("catid"));
				a.setProductId(rs.getInt("prdid"));
				a.setProductName(rs.getString("prdname"));
				a.setProductPrice(rs.getFloat("prdprice"));
				a.setProductDesc(rs.getString("prddesc"));
				a.setProductImage(rs.getString("prdimg"));
				return a;}});return listprd;
	}

	
	public void create(Product p) throws Exception {
		String name=p.getProductName();
       if(p.getProductName()=="") {
    		throw new NullPointerException(name);
			
		}
	else {
		String sql = "INSERT INTO products (catid,prdid,prdname,prdprice,prddesc) values(?,?,?,?,?)";
	       template.update(sql,p.getCategoryId(),p.getProductId(),p.getProductName(),p.getProductPrice(),p.getProductDesc());      
			
	}
	}


	public void update(Product p)  {
		String name=p.getProductName();
		if(p.getProductName()=="") {
			
			throw new NullPointerException(name);
		}
	else {

	
		// TODO Auto-generated method stub
		String sql = "Update products set catid = ?, prdname = ?, prdprice = ?, prddesc = ? where prdid = ?";
		template.update(sql, p.getCategoryId(), p.getProductName(),p.getProductPrice(),p.getProductDesc(),p.getProductId());
	}}


	public void delete(int productId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM products WHERE prdid=?";
		template.update(sql, productId);

	}
	  public List<Product> selectProduct(String catname){
		  
		  String sql = "SELECT products.catid,prdname,prdid,prdprice,prddesc,prdimg from products inner join categories on products.catid = categories.catid where catname='"
		            + catname + "'";

		  
			List<Product> listprd = template.query(sql, new RowMapper<Product>() {
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product a = new Product();
					a.setCategoryId(rs.getInt("catid"));
					a.setProductId(rs.getInt("prdid"));
					a.setProductName(rs.getString("prdname"));
					a.setProductPrice(rs.getFloat("prdprice"));
					a.setProductDesc(rs.getString("prddesc"));
					a.setProductImage(rs.getString("prdimg"));
					return a;
				}

			});

			return listprd;
		  
	  }


}
