package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
      PreparedStatement pstmt = con.prepareStatement("select mno, name, email, pwd, photo, tel, cdt from member");
      ResultSet rs = pstmt.executeQuery()) {
    
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
        PreparedStatement pstmt = con.prepareStatement("select mno, name, email, pwd, photo, tel, cdt"
                                                  + " from member where mno=?"); ) {

      pstmt.setInt(1, no);
      
      try(ResultSet rs = pstmt.executeQuery();){
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
  }

  public int insert(Member member) throws Exception{
    DriverManager.registerDriver(new Driver()); 
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("insert into member (name, email, pwd, photo, tel)"
            + " values(?, ?, ?, ?, ?)");) { 
      
      pstmt.setString(1, member.getName());
      pstmt.setString(2, member.getEmail());
      pstmt.setString(3, member.getPassword());
      pstmt.setString(4, member.getPhoto());
      pstmt.setString(5, member.getTel());
      
    return pstmt.executeUpdate();
    }
  }

  public int update(Member member) throws Exception{
    DriverManager.registerDriver(new Driver()); 
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("update member set name=?, email=?, pwd=?, photo=?, tel=?"
            +" where mno=?");
        ) {
      pstmt.setString(1, member.getName());
      pstmt.setString(2, member.getEmail());
      pstmt.setString(3, member.getPassword());
      pstmt.setString(4, member.getPhoto());
      pstmt.setString(5, member.getTel());
      pstmt.setInt(6, member.getNo());
      
      return pstmt.executeUpdate();
    }
  }

  public int delete(int no) throws Exception{
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      DriverManager.registerDriver(new Driver()); 
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      pstmt = con.prepareStatement("delete from member where mno=?");
      
      pstmt.setInt(1, no);
      
      return pstmt.executeUpdate();

    }catch (Exception e) {
      return 0;
    }

    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {pstmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }
  
  public Member findByEmailPassword(String email, String password) throws Exception {
    DriverManager.registerDriver(new Driver());

    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("select mno, name, email, photo, tel"
            + " from member where email=? and pwd =?");){

      pstmt.setString(1, email);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()){
        if(rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("mno"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPhoto(rs.getString("photo"));
          return member;
        } else {
          return null;
        }
      }
    }
  }
}
