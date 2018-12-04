package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class LessonDetailCommand implements Command {

  Scanner keyboard;

  public LessonDetailCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void execute() {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DriverManager.registerDriver(new Driver()); 
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();

      System.out.print("수업번호? ");
      int lno = Integer.parseInt(keyboard.nextLine());

      rs = stmt.executeQuery("select lno, title, cont, sdt, edt, tot_hr, day_hr, mno"
          + " from lesson"
          + " where lno=" + lno);
      
      if(rs.next()) {
        System.out.printf("수업번호: %d\n", rs.getInt("lno"));
        System.out.printf("수업명: %s\n", rs.getString("title"));
        System.out.printf("설명: %s\n", rs.getString("cont"));
        System.out.printf("시작일: %s\n", rs.getDate("sdt"));
        System.out.printf("종료일: %s\n", rs.getDate("edt"));
        System.out.printf("총수업시간: %d\n", rs.getInt("tot_hr"));  
        System.out.printf("일수업시간: %d\n", rs.getInt("day_hr")); 
        System.out.printf("회원번호: %d\n", rs.getInt("mno")); 
      } else {
        System.out.println("해당 수업을 찾을 수 없습니다.");
      }
    }catch (Exception e) {
      e.printStackTrace();
    }

    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }
}
