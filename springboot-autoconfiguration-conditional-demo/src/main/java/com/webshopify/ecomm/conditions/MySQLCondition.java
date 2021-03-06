package com.webshopify.ecomm.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySQLCondition implements Condition {

//	@Override
//	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//		String dbType = System.getProperty("dbType");
//		if (dbType != null && !dbType.isEmpty() && "mysql".equalsIgnoreCase(dbType))
//			return true;
//		return false;
//	}

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean doesDriverPresent = false;
		try {
			Class mySQLDriver = Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB name:\t" + mySQLDriver.getName());
			if (mySQLDriver != null)
				doesDriverPresent = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + ": this class was not found in classpath");
			doesDriverPresent = false;
		}

		return doesDriverPresent;
	}

}
