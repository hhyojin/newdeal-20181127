package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  
  public MemberUpdateCommand(Scanner keyboard) {
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

      System.out.print("회원번호");
      String mno = keyboard.nextLine();

      ResultSet rs = stmt.executeQuery("select mno, name, email, pwd, photo, tel, cdt"
                                  + " from member"
                                  + " where mno=" + mno);

      rs.next();
      SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
      String oldName = rs.getString("name");
      String oldEmail = rs.getString("email");
      String oldPwd = rs.getString("pwd");
      String oldPhoto = rs.getString("photo");
      String oldTel = rs.getString("tel");
      String oldCdt= transFormat.format(rs.getDate("cdt"));
      rs.close();

      System.out.printf("이름(%s)? \n", oldName);
      System.out.print("이름? ");
      String name = keyboard.nextLine();      
      if (name.equals("")) {name = oldName; }
      
      System.out.printf("이메일(%s)? \n", oldEmail);
      System.out.print("이메일? ");
      String email = keyboard.nextLine();      
      if (email.equals("")) {email = oldEmail; }
      
      System.out.printf("비밀번호(%s)? \n", oldPwd);
      System.out.print("비밀번호? ");
      String pwd = keyboard.nextLine();      
      if (pwd.equals("")) {pwd = oldPwd; }
      
      System.out.printf("사진(%s)? \n", oldPhoto);
      System.out.print("사진? ");
      String photo = keyboard.nextLine();      
      if (photo.equals("")) {photo = oldPhoto; }
      
      System.out.printf("전화(%s)? \n", oldTel);
      System.out.print("전화? ");
      String tel = keyboard.nextLine();      
      if (tel.equals("")) {tel = oldTel; }
      
      System.out.printf("일수업시간(%s)? \n", oldCdt);
      System.out.print("일수업시간? ");
      String cdt = keyboard.nextLine();      
      if (cdt.equals("")) {cdt = oldCdt; }
      
      //SQL을 서버에 전송. select를 제외하고는 모두 executeUpadte 사용.resultSet 노 쓸모
      stmt.executeUpdate("update member set name='" + name + "',"
          + " email='" + email + "',"
          + " pwd='" + pwd + "',"
          + " photo='" + photo + "',"
          + " tel='" + tel + "',"
          + " cdt='" + cdt + "'"
          + " where mno=" + mno);


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
