package com.webshopify.ecomm.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dbType = System.getProperty("dbType");
		if (dbType != null && !dbType.isEmpty() && "mongodb".equalsIgnoreCase(dbType))
			return true;
		return false;
	}

}
