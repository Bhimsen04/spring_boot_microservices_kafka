package com.webshopify.ecomm.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBCondition implements Condition {

//	@Override
//	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//		String dbType = System.getProperty("dbType");
//		if (dbType != null && !dbType.isEmpty() && "mongodb".equalsIgnoreCase(dbType))
//			return true;
//		return false;
//	}

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean doesMongoPresentInClass = false;
		try {
			Class mongodbClass = Class.forName("org.springframework.data.mongodb.SpringDataMongoDB");
			System.out.println("DB name:\t" + mongodbClass.getName());
			if (mongodbClass != null)
				doesMongoPresentInClass = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + ": this class was not found in classpath");
			doesMongoPresentInClass = false;
		}
		return doesMongoPresentInClass;
	}

}
