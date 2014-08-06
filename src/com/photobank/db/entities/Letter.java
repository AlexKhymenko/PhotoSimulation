package com.photobank.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Letter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	private Integer senderId;
	private Integer msgNumber;
	private String msgText;
	public Integer getMemberId() {
		return userId;
	}
	public void setMemberId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public int getMsgNumber() {
		return msgNumber;
	}
	public void setMsgNumber(Integer msgNumber) {
		this.msgNumber = msgNumber;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
}
