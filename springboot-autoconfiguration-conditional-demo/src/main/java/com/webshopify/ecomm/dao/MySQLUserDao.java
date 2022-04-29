package com.webshopify.ecomm.dao;

import java.util.Arrays;
import java.util.List;

public class MySQLUserDao implements UserDao {

	@Override
	public List<String> getAllUsers() {
		return Arrays.asList("MySQLUser1", "MySQLUser2");
	}

}
