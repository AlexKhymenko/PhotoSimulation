package com.photobank.db.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PrivacyStr {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer privacyId;
	private String privacyStr;
    @OneToMany(mappedBy="privacyStr")
    private Set<Photo> photoes;
    
	public Integer getPrivacyId() {
		return privacyId;
	}

	public void setPrivacyId(Integer privacyId) {
		this.privacyId = privacyId;
	}

	public String getPrivacyStr() {
		return privacyStr;
	}

	public void setPrivacyStr(String rivacyStr) {
		this.privacyStr = privacyStr;
	}

}
