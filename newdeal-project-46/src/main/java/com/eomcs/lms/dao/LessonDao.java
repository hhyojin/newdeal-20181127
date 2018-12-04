package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Lesson;

public class LessonDao {

  public List<Lesson> findAll() throws Exception{

    DriverManager.registerDriver(new Driver());

    try ( Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select lno, title, sdt, edt, tot_hr from lesson");){

      ArrayList<Lesson> list = new ArrayList<>();

      while (rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lno"));
        lesson.setTitle(rs.getString("title"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate( rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        list.add(lesson);
      }
      return list;
      
    }catch (Exception e) {
      throw e;
    }
  }

  public Lesson findByNo(int no) throws Exception{

    DriverManager.registerDriver(new Driver()); 
    
    try( Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select lno, title, cont, sdt, edt, tot_hr, day_hr, mno"
            + " from lesson"
            + " where lno=" + no)){

      if(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lno"));
        lesson.setTitle(rs.getString("title"));
        lesson.setContents(rs.getString("cont"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        lesson.setDayHours(rs.getInt("day_hr"));
        lesson.setWriterNo(rs.getInt("mno"));       
        return lesson;       
      } else {
        return null;
      }
    }
  }

  public int insert(Lesson lesson) throws Exception {
    
    DriverManager.registerDriver(new Driver()); 
    int result = 0;
    
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
          Statement stmt = con.createStatement();) {
      //어차피 sql문으로 만들 거니 걍 문자로 해서 보내
       
      //SQL을 서버에 전송. select를 제외하고는 모두 executeUpadte 사용.resultSet 노 쓸모
      result= stmt.executeUpdate("insert into lesson (title, cont, sdt, edt, tot_hr, day_hr, mno)"
                        + " values('" + lesson.getTitle() +"', '" + lesson.getContents() + "', '" 
                        + lesson.getStartDate() + "', '" + lesson.getEndDate() + "', " + lesson.getTotalHours() + ", " 
                        + lesson.getDayHours() + ", " + lesson.getWriterNo() +")");
    }
    return result;
  }

  public int update(Lesson lesson) throws Exception {

    DriverManager.registerDriver(new Driver()); 
    int result =0;
    
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();) {

     result= stmt.executeUpdate("update lesson set title='" + lesson.getTitle() + "',"
          + " cont='" + lesson.getContents() + "',"
          + " sdt='" + lesson.getStartDate() + "',"
          + " edt='" + lesson.getEndDate() + "',"
          + " tot_hr=" + lesson.getTotalHours() + ","
          + " day_hr=" + lesson.getDayHours() + ","
          + " mno=" + lesson.getWriterNo()
          + " where lno=" + lesson.getNo());
    }
    return result; 
  }

  public int delete(int no) throws Exception {
    DriverManager.registerDriver(new Driver());
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();){
     
    return stmt.executeUpdate("delete from lesson where lno=" +no);
  }
}
}
