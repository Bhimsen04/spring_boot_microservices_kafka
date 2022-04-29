package com.webshopify.ecomm.dao;

import java.util.Arrays;
import java.util.List;

public class MongoUserDao implements UserDao {

	@Override
	public List<String> getAllUsers() {
		return Arrays.asList("MongoUser1", "MongoUser2");
	}

}
