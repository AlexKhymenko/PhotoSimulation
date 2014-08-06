package com.photobank.db.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.UniqueConstraint;

@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	//@Id
	private String name;
	private Date regDate;
	private Date updateDate;
	private Integer sharing;
	private String secretKey;
	private Boolean activated;
	private Boolean locked;

	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getSharing() {
		return sharing;
	}
	public void setSharing(Integer sharing) {
		this.sharing = sharing;
	}
	
	public UserRoleStr getUserRoleStr() {
		return userRoleStr;
	}
	public void setUserRoleStr(UserRoleStr userRoleStr) {
		this.userRoleStr = userRoleStr;
	}
	public Set<Photo> getPhotoes() {
		return photoes;
	}
	public void setPhotoes(Set<Photo> photoes) {
		this.photoes = photoes;
	}
	public Password getPassword() {
		return password;
	}
	public void setPassword(Password password) {
		this.password = password;
	}

	public Boolean getActivation() {
		return activated;
	}
	public void setActivation(Boolean activated) {
		this.activated = activated;
	}

	@ManyToOne
	@JoinColumn(name="roleId")
	private UserRoleStr userRoleStr;
	
	@OneToMany(mappedBy = "user")
	private Set<Photo> photoes;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Password password;
	
	@OneToMany(mappedBy = "user")
	private List<UserSession> userSessions;//Set
	/*@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private UserSession userSession;*/
	public List<UserSession> getUserSessions() {
		return userSessions;
	}
	public void setUserSessions(List<UserSession> userSessions) {
		this.userSessions = userSessions;
	}
	


	


	
	

}
