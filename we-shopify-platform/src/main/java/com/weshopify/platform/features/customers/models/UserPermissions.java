package com.weshopify.platform.features.customers.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserPermissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6914823650530076198L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String action;

}
