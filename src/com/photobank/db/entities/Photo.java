package com.photobank.db.entities;


import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
public class Photo {
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer imageId;
	private Date downloadDate;
	private Date updateDate;
	private String folder;
	private String title;
	private String path;
	private Boolean confirm;//field for moderator
	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	//private Blob image;
	private Integer sharing;//there will be memberId

	@ManyToOne
	@JoinColumn(name="idFolder")
	private Catalogue catalogue;
	
	@ManyToOne
	@JoinColumn(name="privacyId")
	private PrivacyStr privacyStr;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "userId", referencedColumnName = "userId"),
        @JoinColumn(name = "name", referencedColumnName = "name"),
    })
	private User user ;*/
	

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

/*	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}*/

	public Integer getSharing() {
		return sharing;
	}

	public void setSharing(Integer sharing) {
		this.sharing = sharing;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	

}
