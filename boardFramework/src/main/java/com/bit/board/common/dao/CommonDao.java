package com.bit.board.common.dao;

public interface CommonDao {

	int getNextSeq();
	void updateHit(int seq);
}
