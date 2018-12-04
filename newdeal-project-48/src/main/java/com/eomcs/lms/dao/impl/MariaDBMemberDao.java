package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MariaDBMemberDao implements MemberDao{

  @Override
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
