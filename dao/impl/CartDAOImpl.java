package com.kgisl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.dao.CartDAO;
import com.kgisl.model.Cart;


public class CartDAOImpl implements CartDAO {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	

	public void save(int userId, int prdId) {

		String sql = "Insert into carts(user_Id,product_Id) values('" + userId + "','" + prdId + "')";
		template.update(sql);
		RowCountCallbackHandler handler = new RowCountCallbackHandler();
		template.query("SELECT user_Id from carts where user_Id='" + userId + "'", handler);
		System.out.println("Row count : " + handler.getRowCount());
		int result = handler.getRowCount();
		String sql11 = "Update  carts set  cart_Count= '" + result + "' where user_Id='" + userId + "'";
		template.update(sql11);

	}

	public List<Cart> getCart(int userId) {

		String sql = "SELECT user_Id,prdname,product_Id,prdprice,prddesc,prdimg,cart_Id from carts inner join products on carts.product_Id = products.prdid where user_Id='" + userId + "'  ";
		List<Cart> listCart = template.query(sql, new RowMapper<Cart>() {
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart a = new Cart();
				a.setProductId(rs.getInt("product_Id"));
				a.setUserId(rs.getInt("user_Id"));
				a.setProductName(rs.getString("prdname"));
				 a.setCartId(rs.getInt("cart_Id"));
				a.setProductPrice(rs.getFloat("prdprice"));
				a.setProductDesc(rs.getString("prddesc"));
                a.setProductImage(rs.getString("prdimg"));
               
				return a;
			}

		});

		return listCart;

	}

	public int count() {
		RowCountCallbackHandler handler = new RowCountCallbackHandler();

		template.query("SELECT user_Id  from carts", handler);
		System.out.println("Row count : " + handler.getRowCount());
		int result = handler.getRowCount();
		return result;

	}

	public void delete(int userid, int cartId) {

		String sql = "DELETE FROM carts WHERE user_Id='" + userid + "' && cart_Id='" + cartId + "'";
		template.update(sql);
		RowCountCallbackHandler handler = new RowCountCallbackHandler();
		template.query("SELECT user_Id from carts where user_Id='" + userid + "'", handler);
		System.out.println("Row count : " + handler.getRowCount());
		int result = handler.getRowCount();
		String sql11 = "Update  carts set  cart_Count= '" + result + "' where user_Id='" + userid + "'";
		template.update(sql11);

	}


}
