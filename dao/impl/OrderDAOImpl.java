package com.kgisl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.dao.OrderDAO;
import com.kgisl.model.Cart;
import com.kgisl.model.Order;

public class OrderDAOImpl implements OrderDAO {
	JdbcTemplate template;
	int userid;
	int productid;
	int orderId;
	float price;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Order> order(Order order) {
		String sql = "INSERT INTO `coedb`.`orders` (`user_id`, `product_id`, `quantity`, `weight`,`cart_Id`) VALUES ('"
				+ order.getUserId() + "', '" + order.getProductId() + "', '" + order.getQuantity() + "', '"
				+ order.getWeight() + "','0')";
		template.update(sql);
		System.out.println("dsgeret3qtet5u");
		String sql1 = "SELECT `order_id`,`prdprice` FROM `orders` inner JOIN `products` ON `orders`.`product_id` = `products`.`prdid`  WHERE `user_id`='"
				+ order.getUserId() + "' && `product_id`='" + order.getProductId() + "'";
		List<Order> listCart = template.query(sql1, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order a = new Order();
				a.setOrderId(rs.getInt("order_id"));
				a.setProductPrice(rs.getFloat("prdprice"));
				return a;
			}
		});

		for (Order o : listCart) {
			orderId = o.getOrderId();
			price = o.getProductPrice();
		}
		System.out.println("ewteryr" + orderId);
		System.out.println("ewteryr" + price);
		String sql2 = "UPDATE `coedb`.`orders` SET `order_price`=('" + price + "'*'" + order.getQuantity() + "'*'"
				+ order.getWeight() + "') WHERE  `order_id`='" + orderId + "'";
		template.update(sql2);
		String sql3 = "	SELECT order_id,catname,prdname,prdprice,prddesc,prdimg,quantity,weight,order_price,user_id,product_id FROM products INNER JOIN categories ON categories.catid = products.catid INNER JOIN `orders` ON `orders`.product_id = products.prdid WHERE `orders`.order_id='"
				+ orderId + "' ";
		List<Order> finallist = template.query(sql3, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order a = new Order();
				a.setOrderId(rs.getInt("order_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setProductId(rs.getInt("product_id"));
				a.setCategoryName(rs.getString("catname"));
				a.setProductName(rs.getString("prdname"));
				a.setProductPrice(rs.getFloat("prdprice"));
				a.setProductDesc(rs.getString("prddesc"));
				a.setProductImage(rs.getString("prdimg"));
				a.setQuantity(rs.getInt("quantity"));
				a.setWeight(rs.getFloat("weight"));
				a.setOrderPrice(rs.getFloat("order_price"));
				return a;
			}
		});
		return finallist;
	}

	
	
	
	
	
	
	

	public List<Order> cartOrder(Order order) {
	System.out.println(order.getCartId());
		String sql = "SELECT * FROM carts WHERE cart_id='" + order.getCartId()  + "'";
		List<Order> listCart = template.query(sql, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order a = new Order();
				a.setProductId(rs.getInt("product_Id"));
				a.setUserId(rs.getInt("user_Id"));
				return a;
			}
			
		});
System.out.println(listCart);
		for (Order r : listCart) {
			System.out.println("forloop");
			userid = r.getUserId();
			productid = r.getProductId();
			System.out.println(productid+"dshrht");
		
		}
		System.out.println(productid+"hiiiiiiiiiiiiiiiiiiiiiiiii");
		System.out.println(productid);
		String sql1 = "INSERT INTO `coedb`.`orders` (`cart_Id`,`user_id`, `product_id`, `quantity`, `weight`) VALUES ('"
				+ order.getCartId() + "','" + userid + "', '" + productid + "', '" + order.getQuantity() + "', '" + order.getWeight() + "')";
		template.update(sql1);

		String sql2 = "SELECT `order_id`,`prdprice` FROM `orders` inner JOIN `products` ON `orders`.`product_id` = `products`.`prdid`  WHERE `user_id`='"
				+ userid + "' && `product_id`='" + productid + "'";
		List<Order> list2 = template.query(sql2, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order a = new Order();
				a.setOrderId(rs.getInt("order_id"));
				a.setProductPrice(rs.getFloat("prdprice"));
				return a;
			}
		});

		for (Order o : list2) {
			orderId = o.getOrderId();
			price = o.getProductPrice();
		}
		System.out.println("ewteryr" + orderId);
		System.out.println("ewteryr" + price);
		String sql3 = "UPDATE `coedb`.`orders` SET `order_price`=('" + price + "'*'" + order.getQuantity() + "'*'" + order.getWeight()
				+ "') WHERE  `order_id`='" + orderId + "'";
		template.update(sql3);
		String sql4 = "	SELECT cart_Id,order_id,catname,prdname,prdprice,prddesc,quantity,weight,order_price,user_id,product_id FROM products INNER JOIN categories ON categories.catid = products.catid INNER JOIN `orders` ON `orders`.product_id = products.prdid WHERE `orders`.order_id='"
				+ orderId + "' ";
		List<Order> finallist = template.query(sql4, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order a = new Order();
				a.setOrderId(rs.getInt("order_id"));
				a.setCartId(rs.getInt("cart_Id"));
				a.setUserId(rs.getInt("user_id"));
				a.setProductId(rs.getInt("product_id"));
				a.setCategoryName(rs.getString("catname"));
				a.setProductName(rs.getString("prdname"));
				a.setProductPrice(rs.getFloat("prdprice"));
				a.setProductDesc(rs.getString("prddesc"));
				a.setQuantity(rs.getInt("quantity"));
				a.setWeight(rs.getFloat("weight"));
				a.setOrderPrice(rs.getFloat("order_price"));

				return a;
			}
		});
		return finallist;

	}

	public List<Order> get(int userId) {
	

			String sql = "	SELECT cart_Id,order_id,catname,prdname,prdimg,prdprice,prddesc,quantity,weight,order_price,user_id,product_id FROM products INNER JOIN categories ON categories.catid = products.catid INNER JOIN `orders` ON `orders`.product_id = products.prdid WHERE `orders`.user_id='"+userId+"' ";
			List<Order> listCart = template.query(sql, new RowMapper<Order>() {
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order a = new Order();
					a.setOrderId(rs.getInt("order_id"));
					a.setProductId(rs.getInt("product_id"));
					a.setCategoryName(rs.getString("catname"));
					a.setProductName(rs.getString("prdname"));
					a.setProductImage(rs.getString("prdimg"));
					a.setProductPrice(rs.getFloat("prdprice"));
					a.setProductDesc(rs.getString("prddesc"));
					a.setQuantity(rs.getInt("quantity"));
					a.setWeight(rs.getFloat("weight"));
					a.setOrderPrice(rs.getFloat("order_price"));
					a.setCartId(rs.getInt("cart_Id"));
					a.setUserId(rs.getInt("user_id"));
					
					return a;
				}

			});

			return listCart;

		

	}
	
}
