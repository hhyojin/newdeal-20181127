package com.bit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.board.common.dao.CommonDao;
import com.bit.board.dao.ReboardDao;
import com.bit.board.model.ReboardDto;
import com.bit.util.BoardConstance;

@Service
public class ReboardServiceImpl implements ReboardService{

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int seq=sqlsession.getMapper(CommonDao.class).getNextSeq();
		reboardDto.setSeq(seq);
		reboardDto.setRef(seq);
		int cnt = sqlsession.getMapper(ReboardDao.class).writeArticle(reboardDto);
		return cnt != 0 ? seq : 0;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> param) {
		int pg = Integer.parseInt(param.get("pg"));
		/*쿼리상 마지막 게시글 번호 구하는 게 나음*/
		int end = pg * BoardConstance.LIST_COUNT;
		int start = end - BoardConstance.LIST_COUNT;
		param.put("start", start+"");
		param.put("end", end + "");
		return sqlsession.getMapper(ReboardDao.class).listArticle(param);
	}

	@Override
	public ReboardDto viewArticle(int seq) {	
		sqlsession.getMapper(CommonDao.class).updateHit(seq); //view 화면에서 내 조회수 반영 됨
		ReboardDto reboardDto = sqlsession.getMapper(ReboardDao.class).viewArticle(seq);
		if(reboardDto !=null) {
			reboardDto.setContent(reboardDto.getContent().replace("\n", "<br>"));
			//view 화면에서 내 조회수는 반영 안 됨.
			//sqlsession.getMapper(CommonDao.class).updateHit(seq);
		}
		return reboardDto;
	}
	
	@Override
	public ReboardDto getArticle(int seq) {	//view 하면 replace에 조회수도 늘어서 따로 뺌
		return sqlsession.getMapper(ReboardDao.class).viewArticle(seq);
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public void modifyArticle(ReboardDto reboardDto) {
		
		
	}

	@Override
	public void deleteArticle(int seq) {
		
		
	}


}
