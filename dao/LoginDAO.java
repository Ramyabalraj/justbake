package com.kgisl.dao;

import java.util.List;


import com.kgisl.model.Login;

public interface LoginDAO {
	public List<Login> get();
	public List<Login> getid(String username,String password);
}
