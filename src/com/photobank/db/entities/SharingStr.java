package com.photobank.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SharingStr {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sharingId;
	private String sharingStr;

	public Integer getSharingId() {
		return sharingId;
	}

	public void setSharingId(Integer sharingId) {
		this.sharingId = sharingId;
	}

	public String getSharingStr() {
		return sharingStr;
	}

	public void setSharingStr(String sharingStr) {
		this.sharingStr = sharingStr;
	}

}
