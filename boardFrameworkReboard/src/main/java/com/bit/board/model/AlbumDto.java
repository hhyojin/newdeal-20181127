package com.bit.board.model;

public class AlbumDto extends BoardDto{

	private int aseq;
	private String orginPicture;
	private String savePicture;
	private String savefolder;
	private int type; //사진이 가로타입인지 세로타입인지
	
	
	public int getAseq() {
		return aseq;
	}
	public void setAseq(int aseq) {
		this.aseq = aseq;
	}
	public String getOrginPicture() {
		return orginPicture;
	}
	public void setOrginPicture(String orginPicture) {
		this.orginPicture = orginPicture;
	}
	public String getSavePicture() {
		return savePicture;
	}
	public void setSavePicture(String savePicture) {
		this.savePicture = savePicture;
	}
	public String getSavefolder() {
		return savefolder;
	}
	public void setSavefolder(String savefolder) {
		this.savefolder = savefolder;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
