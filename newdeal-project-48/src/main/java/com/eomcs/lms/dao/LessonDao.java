package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Lesson;

public class LessonDao {

  public List<Lesson> findAll() throws Exception{
    
    DriverManager.registerDriver(new Driver());
   
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select lno, title, sdt, edt, tot_hr from lesson");

      ArrayList<Lesson> list = new ArrayList<>();
      
      while (rs.next()) {
        Lesson lesson = new Lesson();
        
        lesson.setNo(rs.getInt("lno"));
        lesson.setContents(rs.getString("title"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate( rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        list.add(lesson);
      }
      
      return list;
      
    }catch (Exception e) {
      throw e;
    }
    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {rs.close();} catch(Exception e) {}
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }
  
  public Lesson findByNo(int no) throws Exception{
    
    return null;
  }
  
  public void insert(Lesson lesson) throws Exception {
    
  }
  
  public void update(Lesson lesson) throws Exception {
    
  }
  
  public void delete(int no) throws Exception {
    
  }
}
