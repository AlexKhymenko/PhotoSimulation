package com.photobank.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
 
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Password {
	@Id
	    @Column(name="userId", unique=true, nullable=false)
	    @GeneratedValue(generator="gen")
	    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
	private Integer userId;
	private String password;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
