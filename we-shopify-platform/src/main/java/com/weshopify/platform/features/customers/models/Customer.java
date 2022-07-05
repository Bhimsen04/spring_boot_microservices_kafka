package com.weshopify.platform.features.customers.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = -3392255258797133035L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private boolean selfReg;
	private String firstName;
	private String lastName;

	@Column(unique = true, nullable = false, name = "username")
	private String userName; // bydefault it is user_name

	@Column(unique = true, nullable = false)
	private String email;

	// @Column(unique = true, updatable = true, nullable = false)
	private String mobileNumber;

	@Column(nullable = false, updatable = true)
	private String password;

	@OneToOne // (cascade = CascadeType.ALL)
	private UserRole role;

}
