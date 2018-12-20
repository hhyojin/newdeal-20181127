package com.bit.board.admin.service;

import java.util.List;

import com.bit.board.admin.model.BoardListDto;
import com.bit.board.admin.model.CategoryDto;
import com.bit.board.model.BoardDto;

public interface BoardAdminService {

	List<BoardListDto> getBoardMenu(); // 메뉴 목록 불러오는 것. 
	
	List<CategoryDto> getCategory(); //카테고리 중복 검사 + 게시판 만들기 전에 어떤 카테고리에 만들 건지
	void makeCategory(CategoryDto categoryDto);
	
	List<BoardDto> getBoardType(); //어떤 종류의 게시판 만들 건지
	void makeBoard(BoardListDto boardListDto);
}
