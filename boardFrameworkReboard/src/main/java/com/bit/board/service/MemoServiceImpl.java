package com.bit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.board.dao.MemoDao;
import com.bit.board.model.MemoDto;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int writeMemo(MemoDto memoDto) {
		return sqlSession.getMapper(MemoDao.class).writeMemo(memoDto);
	}

	@Override
	public String listMemo(int seq) {
		List<MemoDto> list=sqlSession.getMapper(MemoDao.class).listMemo(seq);
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for(MemoDto memoDto : list) {
			JSONObject memo = new JSONObject();
			memo.put("mseq", memoDto.getMseq());
			memo.put("seq", memoDto.getSeq());
			memo.put("id", memoDto.getId());
			memo.put("name", memoDto.getName());
			memo.put("mcontent", memoDto.getMcontent());
			memo.put("mtime", memoDto.getMtime());
			
			jsonArr.put(memo);
		}
		json.put("memolist", jsonArr);
		return json.toString();
	}

	@Override
	public int modifyMemo(MemoDto memoDto) {
		return sqlSession.getMapper(MemoDao.class).modifyMemo(memoDto);
	}

	@Override
	public int deleteMemo(int mseq) {
		return sqlSession.getMapper(MemoDao.class).deleteMemo(mseq);
	}

}
