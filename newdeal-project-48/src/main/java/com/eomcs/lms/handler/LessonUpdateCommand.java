package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command {

  Scanner keyboard;

  public LessonUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void execute() {
    
    Connection con = null;
    Statement stmt = null;


    try {
      DriverManager.registerDriver(new Driver()); 
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();

      System.out.print("수업 번호");
      String lno = keyboard.nextLine();

      ResultSet rs = stmt.executeQuery("select title, cont, sdt, edt, tot_hr, day_hr,"
          + " mno from lesson where lno=" + lno);

      rs.next();
      SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
      String oldTitle = rs.getString("title");
      String oldContent = rs.getString("cont");
      String oldStardDate = transFormat.format(rs.getDate("sdt"));
      String oldEndDate = transFormat.format(rs.getDate("edt"));
      String oldTotalHours = Integer.toString(rs.getInt("tot_hr"));
      String oldDayHours = Integer.toString(rs.getInt("day_hr"));
      String oldMno = Integer.toString(rs.getInt("mno"));
      rs.close();

      System.out.printf("제목(%s)? \n", oldTitle);
      System.out.print("수업제목? ");
      String title = keyboard.nextLine();      
      if (title.equals("")) {title = oldTitle; }
      
      System.out.printf("내용(%s)? \n", oldContent);
      System.out.print("수업내용? ");
      String content = keyboard.nextLine();      
      if (content.equals("")) {content = oldContent; }
      
      System.out.printf("시작일(%s)? \n", oldStardDate);
      System.out.print("시작일? ");
      String sdt = keyboard.nextLine();      
      if (sdt.equals("")) {sdt = oldStardDate; }
      
      System.out.printf("종료일(%s)? \n", oldEndDate);
      System.out.print("종료일? ");
      String edt = keyboard.nextLine();      
      if (edt.equals("")) {edt = oldEndDate; }
      
      System.out.printf("총수업시간(%s)? \n", oldTotalHours);
      System.out.print("총수업시간? ");
      String tot_hr = keyboard.nextLine();      
      if (tot_hr.equals("")) {tot_hr = oldTotalHours; }
      
      System.out.printf("일수업시간(%s)? \n", oldDayHours);
      System.out.print("일수업시간? ");
      String day_hr = keyboard.nextLine();      
      if (day_hr.equals("")) {day_hr = oldDayHours; }
      
      System.out.printf("회원번호(%s)? \n", oldMno);
      System.out.print("회원번호? ");
      String mno = keyboard.nextLine();      
      if (mno.equals("")) {mno = oldMno; }
     

      //SQL을 서버에 전송. select를 제외하고는 모두 executeUpadte 사용.resultSet 노 쓸모
      stmt.executeUpdate("update lesson set title='" + title + "',"
          + " cont='" + content + "',"
          + " sdt='" + sdt + "',"
          + " edt='" + edt + "',"
          + " tot_hr=" + tot_hr + ","
          + " day_hr=" + day_hr + ","
          + " mno=" + mno
          + " where lno=" + lno);


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
