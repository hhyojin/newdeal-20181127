package com.jquery.ajax.comment;

public class CommentVO {
	
	private int seq;
	private String comment;
	
	public CommentVO(){}
	public CommentVO(int seq , String comment){
		this.seq = seq;
		this.comment = comment;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
