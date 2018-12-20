package com.bit.board.dao;

import java.util.List;
import java.util.Map;

import com.bit.board.model.ReboardDto;

public interface BoardDao {

	int writeArticle(ReboardDto reboardDto);
	List<ReboardDto> listArticle(Map<String, String> param);
	ReboardDto viewArticle(int seq);
	int replyArticle(ReboardDto reboardDto);
	
	void modifyArticle(ReboardDto reboardDto);
	void deleteArticle(int seq);
}
