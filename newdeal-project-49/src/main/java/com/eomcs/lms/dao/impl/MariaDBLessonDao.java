package com.eomcs.lms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class MariaDBLessonDao implements LessonDao {

  SqlSessionFactory sqlSessionFactory;

  public MariaDBLessonDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Lesson> findAll() throws Exception{
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("LessonDao.findAll");
    }
  }

  public Lesson findByNo(int no) throws Exception{
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("LessonDao.findByNo", no);
    }
  }

  public int insert(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("LessonDao.insert", lesson);
      sqlSession.commit();
      return count;
    }
  }

  public int update(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("LessonDao.update", lesson);
      sqlSession.commit();
      return count;
    }
  }

  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("LessonDao.delete", no);
      sqlSession.commit();
      return count;
    }
  }
}

