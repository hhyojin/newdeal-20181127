package com.bit.board.dao;

import java.util.List;
import java.util.Map;

import com.bit.board.model.AlbumDto;

public interface AlbumDao {
	   int writeArticle(AlbumDto albumDto);
	   List<AlbumDto> listArticle(Map<String, String> param);
	   AlbumDto viewArticle(int seq);
		
	   void modifyArticle(AlbumDto albumDto);
	   void deleteArticle(int seq);
}
