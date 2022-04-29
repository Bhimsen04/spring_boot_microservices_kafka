package com.webshopify.ecomm.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySQLCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dbType = System.getProperty("dbType");
		if (dbType != null && !dbType.isEmpty() && "mysql".equalsIgnoreCase(dbType))
			return true;
		return false;
	}

}
