package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command {
  
  Scanner keyboard;
  
  public MemberListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  public void execute() {
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select mno, name, email, pwd, photo, tel, cdt from member");
      
      //DBMS에서 한 개의 레코드를 가져온다
      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %s, %s\n", 
            rs.getInt("mno"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("pwd"),
            rs.getString("tel"),
            rs.getDate("cdt")
            );
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
