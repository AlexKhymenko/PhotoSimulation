package com.photobank.db.entities;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Catalogue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idFolder;
	private String nameDirectory;
	private Integer parentId;
	
	@OneToMany(mappedBy="catalogue")
	private Set<Photo> photoes;
	

	public Integer getIdFolder() {
		return idFolder;
	}

	public void setIdFolder(Integer idFolder) {
		this.idFolder = idFolder;
	}

	public String getNameDirectory() {
		return nameDirectory;
	}

	public void setNameDirectory(String nameDirectory) {
		this.nameDirectory = nameDirectory;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
