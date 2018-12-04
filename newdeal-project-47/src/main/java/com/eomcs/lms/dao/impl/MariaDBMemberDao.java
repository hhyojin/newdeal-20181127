package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MariaDBMemberDao implements MemberDao {
  
  public List<Member> findAll() throws Exception{ 
  
  DriverManager.registerDriver(new Driver());
  try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select mno, name, email, pwd, photo, tel, cdt from member")) {
    
    ArrayList<Member> list = new ArrayList<>();
    
    while (rs.next()) {
     Member member = new Member();
     member.setNo(rs.getInt("mno"));
     member.setName(rs.getString("name"));
     member.setEmail(rs.getString("email"));
     member.setPassword(rs.getString("pwd"));
     member.setTel(rs.getString("tel"));
     member.setRegisteredDate(rs.getDate("cdt"));
     list.add(member);
    }
    return list;
    }
  }

  public Member findByNo(int no) throws Exception {

    DriverManager.registerDriver(new Driver());
    
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select mno, name, email, pwd, photo, tel, cdt"
            + " from member"
            + " where mno=" + no);) {

      //DBMS에서 한 개의 레코드를 가져온다
      if(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("mno"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setPhoto(rs.getString("photo"));
        member.setTel(rs.getString("tel"));
        member.setRegisteredDate(rs.getDate("cdt"));
        return member;
      } else {
        return null;
      }
    }
  }

  public int insert(Member member) throws Exception{
    DriverManager.registerDriver(new Driver()); 
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();) {
    
    return stmt.executeUpdate("insert into member (name, email, pwd, photo, tel)"
                        + " values('" + member.getName() + "', '" + member.getEmail() + "', '" 
                        + member.getPassword() + "', '" + member.getPassword() + "', '" + member.getTel()+ "')");
    }
  }

  public int update(Member member) throws Exception{
    DriverManager.registerDriver(new Driver()); 
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ) {

      return stmt.executeUpdate("update member set name='" + member.getName() + "',"
          + " email='" + member.getEmail() + "',"
          + " pwd='" + member.getPassword() + "',"
          + " photo='" + member.getPhoto() + "',"
          + " tel='" + member.getTel() + "',"
          + " cdt='" + member.getRegisteredDate() + "'"
          + " where mno=" + member.getNo());
    }
  }

  public int delete(int no) throws Exception{
    Connection con = null;
    Statement stmt = null;

    try {
      DriverManager.registerDriver(new Driver()); 
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();

      return stmt.executeUpdate("delete from member where mno=" + no);

    }catch (Exception e) {
      return 0;
    }

    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }
  
}
