package com.bit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.board.common.dao.CommonDao;
import com.bit.board.dao.AlbumDao;
import com.bit.board.model.AlbumDto;

@Service
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public int writeArticle(AlbumDto albumDto) {
		int seq = sqlsession.getMapper(CommonDao.class).getNextSeq();
		albumDto.setSeq(seq);
		return sqlsession.getMapper(AlbumDao.class).writeArticle(albumDto);
	}

	@Override
	public List<AlbumDto> listArticle(Map<String, String> param) {
		return null;
	}

	@Override
	public AlbumDto viewArticle(int seq) {
		return null;
	}

	@Override
	public AlbumDto getArticle(int seq) {
		return null;
	}

	@Override
	public void modifyArticle(AlbumDto albumDto) {	
	}

	@Override
	public void deleteArticle(int seq) {
		// TODO Auto-generated method stub
		
	}

}
