package com.photobank.db.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Log {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer photoId;
	private Integer userId;
	private Date data;
	private String log;
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = new Date();//Set current date
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	
	
	

}
