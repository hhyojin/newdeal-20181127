package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Member;

public class MemberDetailCommand implements Command {
  
  Scanner keyboard;
  
  public MemberDetailCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  public void execute() {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      System.out.print("회원번호? ");
      int mno = Integer.parseInt(keyboard.nextLine());
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select mno, name, email, pwd, photo, tel, cdt"
                              + " from member"
                              + " where mno=" + mno);

      //DBMS에서 한 개의 레코드를 가져온다
      if(rs.next()) {
        System.out.printf("회원번호: %d\n", rs.getInt("mno"));
        System.out.printf("이름: %s\n", rs.getString("name"));
        System.out.printf("이메일: %s\n", rs.getString("email"));
        System.out.printf("비밀번호: %s\n", rs.getString("pwd"));
        System.out.printf("사진: %s\n", rs.getString("photo"));
        System.out.printf("전화번호: %s\n", rs.getString("tel"));  
        System.out.printf("가입일자: %s\n", rs.getDate("cdt")); 
      } else {
        System.out.println("해당 번호의 게시물이 없습니다.");
      }
    }catch (Exception e) {
      e.printStackTrace();
    }

    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {rs.close();} catch(Exception e) {}
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }
}
