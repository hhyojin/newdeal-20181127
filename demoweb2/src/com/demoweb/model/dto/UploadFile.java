package com.demoweb.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class UploadFile implements Serializable {

	private int uploadFileNo;
	private int uploadNo;
	private String userFileName;
	private String savedFileName;
	private Date regDate;
	private int downloadCount;
	
	public UploadFile() {}

	public int getUploadFileNo() {
		return uploadFileNo;
	}

	public void setUploadFileNo(int uploadFileNo) {
		this.uploadFileNo = uploadFileNo;
	}

	public int getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(int uploadNo) {
		this.uploadNo = uploadNo;
	}

	public String getUserFileName() {
		return userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	
}
