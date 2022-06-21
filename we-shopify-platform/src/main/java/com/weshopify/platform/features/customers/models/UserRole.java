package com.weshopify.platform.features.customers.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class UserRole implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 373941817363618574L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String role;
	private String description;
	private BigDecimal value;

	@OneToMany(mappedBy = "role" , cascade = CascadeType.ALL)
	private List<RoleToPermissions> actions; // third table: user_role_actions

}
