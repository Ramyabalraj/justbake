package com.kgisl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.dao.LoginDAO;
import com.kgisl.model.Category;
import com.kgisl.model.Login;

public class LoginDAOImpl implements LoginDAO {

	JdbcTemplate template;

	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Login> get() {
		// TODO Auto-generated method stub
		String sql = "SELECT username,password,userId from login";
		List<Login> list = template.query(sql, new RowMapper<Login>() {
			public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
				Login a = new Login();
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setUserId(rs.getInt("userId"));
				
				return a;
			}

		});

		return list;
		}

	public List<Login> getid(String username,String password) {

		// TODO Auto-generated method stub
		String sql = "SELECT username,password,userId from login where username='"+username+"' AND password='"+password+"'";
		List<Login> list = template.query(sql, new RowMapper<Login>() {
			public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
				Login a = new Login();
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setUserId(rs.getInt("userId"));
				return a;
			}

		});
		return list;
	}

}
