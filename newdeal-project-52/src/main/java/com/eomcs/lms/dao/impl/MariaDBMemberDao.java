package com.eomcs.lms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

//Spring IoC 컨테이너에게 이 클래스의 인스턴스를 자동생성하게 표시한다.
@Component
public class MariaDBMemberDao implements MemberDao{

  SqlSessionFactory sqlSessionFactory;

  public MariaDBMemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }


  public Member findByEmailPassword(String email, String password) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession())  {

      HashMap<String,Object> params = new HashMap<>();
      params.put("email", email);
      params.put("password", password);

      return sqlSession.selectOne("MemberDao.findByEmailPassword", params);
      //여기의 no는 auto-boxing. 내부적으로는 integer
    }
  }

  public List<Member> findAll() throws Exception{     
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberDao.findAll");
    }
  }

  public Member findByNo(int no) throws Exception {   
    try (SqlSession sqlSession = sqlSessionFactory.openSession() ) {
      return sqlSession.selectOne("MemberDao.findByNo", no);     
    }
  }

  public int insert(Member member) throws Exception{
    try (SqlSession sqlSession = sqlSessionFactory.openSession() ) {
      int count = sqlSession.insert("MemberDao.insert", member); 
      sqlSession.commit();
      return count;
    }
  }

  public int update(Member member) throws Exception{
    try (SqlSession sqlSession = sqlSessionFactory.openSession() ) {
      int count = sqlSession.update("MemberDao.update", member);
      sqlSession.commit();
      return count;
    }
  }

  public int delete(int no) throws Exception{
    try (SqlSession sqlSession = sqlSessionFactory.openSession() ) {
      int count = sqlSession.delete("MemberDao.delete", no);
      sqlSession.commit();
      return count;
    }
  }


}
