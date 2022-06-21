package com.weshopify.platform;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.weshopify.platform.features.customers.models.RoleToPermissions;
import com.weshopify.platform.features.customers.models.UserPermissions;
import com.weshopify.platform.features.customers.models.UserRole;
import com.weshopify.platform.features.customers.repository.UserPermissionsDataRepo;
import com.weshopify.platform.features.customers.repository.UserRolesDataRepo;

@Import(value= CustomerPermissionsTest.class)
public class CustomerRolesTest extends WeShopifyPlatformApplicationTests {

	@Autowired
	private UserPermissionsDataRepo permissionDataRepo;

	@Autowired
	private UserRolesDataRepo roleDataRepo;

	@Test
	public void testCreateRole() {
		UserRole role = new UserRole();
		role.setRole("Admin");
		role.setDescription("admin");
		List<UserPermissions> permissionList = permissionDataRepo.findAll();
		assertNotNull(permissionList);
		assertNotNull(permissionList.size());
		List<RoleToPermissions> roleToActionList = new ArrayList<RoleToPermissions>();
		permissionList.stream().forEach(permission -> {
			RoleToPermissions roleToAction = new RoleToPermissions();
			roleToAction.setPermissions(permission);
			roleToAction.setRole(role);
			
			roleToActionList.add(roleToAction);
		});
		role.setActions(roleToActionList);
		roleDataRepo.save(role);
		assertNotNull(role.getId());
	}

}
