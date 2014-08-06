package com.photobank.db.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserRoleStr {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer roleId;
	private String roleStr;

	@OneToMany(mappedBy="userRoleStr")
	private Set<User> users;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

}
