package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;


public class BoardUpdateCommand implements Command{

  Scanner keyboard;

  public BoardUpdateCommand (Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void execute() {

    Connection con = null;
    Statement stmt = null;


    try {
      DriverManager.registerDriver(new Driver()); 
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();

      System.out.print("게시글 번호? ");
      String no = keyboard.nextLine();

      ResultSet rs = stmt.executeQuery("select cont from board where bno=" + no);

      rs.next();
      String oldContent = rs.getString("cont");
      rs.close();

      System.out.printf("내용(%s)? \n", oldContent);

      System.out.print("게시글 내용? ");
      String content = keyboard.nextLine();

      //SQL을 서버에 전송. select를 제외하고는 모두 executeUpadte 사용.resultSet 노 쓸모
      stmt.executeUpdate("update board set cont='" + content
          + "' where bno=" + no);


      //DBMS에서 한 개의 레코드를 가져올 필요 없음
      System.out.println("변경했습니다.");

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
