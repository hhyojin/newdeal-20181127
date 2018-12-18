package com.demoweb.model.dto;

import java.sql.Date;

public class BoardComment {

	private int commentNo;
	private int boardNo;
	private String writer;
	private String content;
	private Date regDate;
	private String regDate2;

  public String getRegDate2() {
    return regDate2;
  }
  public void setRegDate2(String regDate2) {
    this.regDate2 = regDate2;
  }
  public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
