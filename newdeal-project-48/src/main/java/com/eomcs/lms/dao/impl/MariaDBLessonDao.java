package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class MariaDBLessonDao implements LessonDao {

  public List<Lesson> findAll() throws Exception{

    DriverManager.registerDriver(new Driver());

    try ( Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("select lno, title, sdt, edt, tot_hr from lesson");
        ResultSet rs = pstmt.executeQuery();){

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
        PreparedStatement pstmt = con.prepareStatement("select lno, title, cont, sdt, edt, tot_hr, day_hr, mno"
            + " from lesson where lno=?");){

      pstmt.setInt(1, no);
      
      try(ResultSet rs = pstmt.executeQuery()){
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
  }

  public int insert(Lesson lesson) throws Exception {
    
    DriverManager.registerDriver(new Driver()); 
    int result = 0;
    
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("insert into lesson (title, cont, sdt, edt, tot_hr, day_hr, mno)"
            + " values(?, ?, ?, ?, ?, ?, ?)")) {
      
      pstmt.setString(1, lesson.getTitle());
      pstmt.setString(2, lesson.getContents());
      pstmt.setDate(3, lesson.getStartDate());
      pstmt.setDate(4, lesson.getEndDate());
      pstmt.setInt(5, lesson.getTotalHours());
      pstmt.setInt(6, lesson.getDayHours());
      pstmt.setInt(7, lesson.getWriterNo());
   
      result= pstmt.executeUpdate();
    }
    return result;
  }

  public int update(Lesson lesson) throws Exception {

    DriverManager.registerDriver(new Driver()); 
    int result =0;
    
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("update lesson set title=?, cont=?,"
            + " sdt=?, edt=?, tot_hr=?, day_hr=?, mno=? where lno=?")) {

      pstmt.setString(1, lesson.getTitle());
      pstmt.setString(2, lesson.getContents());
      pstmt.setDate(3, lesson.getStartDate());
      pstmt.setDate(4, lesson.getEndDate());
      pstmt.setInt(5, lesson.getTotalHours());
      pstmt.setInt(6, lesson.getDayHours());
      pstmt.setInt(7, lesson.getWriterNo());
      pstmt.setInt(8, lesson.getNo());
      
     result= pstmt.executeUpdate();
    }
    return result; 
  }

  public int delete(int no) throws Exception {
    DriverManager.registerDriver(new Driver());
    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("delete from lesson where lno=?");){
     
     pstmt.setInt(1, no); 
      
    return pstmt.executeUpdate();
  }
}
}
