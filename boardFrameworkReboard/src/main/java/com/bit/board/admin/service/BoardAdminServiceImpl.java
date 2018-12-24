package com.bit.board.admin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.board.admin.dao.BoardAdminDao;
import com.bit.board.admin.model.BoardListDto;
import com.bit.board.admin.model.CategoryDto;
import com.bit.board.model.BoardDto;

@Service
public class BoardAdminServiceImpl implements BoardAdminService{

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public List<BoardListDto> getBoardMenu() {
		return sqlsession.getMapper(BoardAdminDao.class).getBoardMenu();
	}

	@Override
	public List<CategoryDto> getCategory() {
	
		return null;
	}

	@Override
	public void makeCategory(CategoryDto categoryDto) {
		
		
	}

	@Override
	public List<BoardDto> getBoardType() {
	
		return null;
	}

	@Override
	public void makeBoard(BoardListDto boardListDto) {
		
		
	}

}
