package com.webshopify.ecomm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import com.webshopify.ecomm.dao.UserDao;

@SpringBootApplication
public class SpringbootAutoconfigurationConditionalDemoApplication implements CommandLineRunner {
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private Environment env;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAutoconfigurationConditionalDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("profile configured: " + env.getActiveProfiles());
//		System.out.println(dataSource.toString());
		System.out.println(userDao.getAllUsers());
		for (String beanName : applicationContext.getBeanDefinitionNames())
			System.out.println("BeanNames: " + beanName);
	}

}
