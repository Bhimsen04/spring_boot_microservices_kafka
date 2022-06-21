package com.weshopify.platform;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopify.platform.features.customers.models.UserPermissions;
import com.weshopify.platform.features.customers.repository.UserPermissionsDataRepo;

public class CustomerPermissionsTest extends WeShopifyPlatformApplicationTests {

	@Autowired
	private UserPermissionsDataRepo dataRepo;

	String action = null;

	@BeforeEach
	public void init() {
		this.action = "view_cusomers";
	}

	@Test
	public void testCreatePermission() {
		UserPermissions permissions = new UserPermissions();
		permissions.setAction(action);
		dataRepo.save(permissions);
		assertNotNull(permissions.getId());
	}
}
