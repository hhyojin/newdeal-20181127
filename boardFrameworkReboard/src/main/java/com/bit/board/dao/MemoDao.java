package com.bit.board.dao;

import java.util.List;

import com.bit.board.model.MemoDto;

public interface MemoDao {

	int writeMemo(MemoDto memoDto);
	List<MemoDto> listMemo(int seq);
	int modifyMemo(MemoDto memoDto);
	int deleteMemo(int mseq);
	
}
