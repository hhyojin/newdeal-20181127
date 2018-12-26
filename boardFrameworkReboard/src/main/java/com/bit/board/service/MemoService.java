package com.bit.board.service;

import com.bit.board.model.MemoDto;

public interface MemoService {

	int writeMemo(MemoDto memoDto);
	String listMemo(int seq);
	int modifyMemo(MemoDto memoDto);
	int deleteMemo(int mseq);
	
}
