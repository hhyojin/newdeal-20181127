package com.demoweb.model.dto;

import java.sql.Date;
import java.util.ArrayList;

public class Board {
	
	private int boardNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int readCount;
	private boolean deleted;
	
	//글의 논리적인 순서번호를 관리하기 위한 필드 (3개)
	private int groupNo;
	private int step;
	private int depth;
	
	//데이터베이싀 Board 테이블과 BoardComment 테이블 사이의
	//1 : Many 관계를 구현하는 필드
	private ArrayList<BoardComment> comments;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public ArrayList<BoardComment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<BoardComment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + ", readCount=" + readCount + ", deleted=" + deleted + ", groupNo=" + groupNo
				+ ", step=" + step + ", depth=" + depth + ", comments=" + comments + "]";
	}
	
	
}
