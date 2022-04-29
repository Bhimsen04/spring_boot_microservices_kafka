package com.webshopify.ecomm.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.webshopify.ecomm.conditions.MongoDBCondition;
import com.webshopify.ecomm.conditions.MySQLCondition;
import com.webshopify.ecomm.dao.MongoUserDao;
import com.webshopify.ecomm.dao.MySQLUserDao;
import com.webshopify.ecomm.dao.UserDao;

@Configuration
public class AppConfig {

//	@Bean
//	@Profile("mysql")
//	public DataSource mySqlDs() {
//		System.out.println("MySQL DB is connecting");
//		DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/shopifyme", "root", "bhim");
//		return dataSource;
//	}
//
//	@Bean
//	@Profile("h2")
//	public DataSource h2DB() {
//		System.out.println("h2 DB is connecting");
//		DataSource dataSource = new DriverManagerDataSource("jdbc:h2:testDb", "sa", "");
//		return dataSource;
//	}
	
	@Bean
	@Conditional(MySQLCondition.class)
	public UserDao mySQLUserDao() {
		return new MySQLUserDao();
	}
	
	@Bean
	@Conditional(MongoDBCondition.class)
	public UserDao mongoUserDao() {
		return new MongoUserDao();
	}
	
}
