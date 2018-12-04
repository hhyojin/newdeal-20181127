package com.eomcs.lms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class MariaDBBoardDao implements BoardDao{

  SqlSessionFactory sqlSessionFactory;

  public MariaDBBoardDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Board> findAll() throws Exception{ //ArrayList로 하면 안 됨. 유연성을 위해 인터페이스로 적어

    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      /*      ArrayList<Board> list = new ArrayList<>();*/
      return sqlSession.selectList("BoardDao.findAll");
    }
  }

  public Board findByNo(int no) throws Exception{
    try(SqlSession sqlSession = sqlSessionFactory.openSession())  {
      return sqlSession.selectOne("BoardDao.findByNo", no);
      //여기의 no는 auto-boxing. 내부적으로는 integer
    }
  }

  public int insert(Board board) throws Exception{
    try (SqlSession sqlSession = sqlSessionFactory.openSession()){  
      int count =  sqlSession.insert("BoardDao.insert", board);
      sqlSession.commit();
      return count;
    }
  }

  public int update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()){
      int count =  sqlSession.update("BoardDao.update", board); 
      sqlSession.commit();
      return count;
    }
  }

  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count =  sqlSession.delete("BoardDao.delete", no); 
      sqlSession.commit();
      return count;
    }
  }
}