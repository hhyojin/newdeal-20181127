package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Board;

public class BoardDao {
  public List<Board> findAll() throws Exception{ //ArrayList로 하면 안 됨. 유연성을 위해 인터페이스로 적어

    DriverManager.registerDriver(new Driver());

    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select bno, cont, cdt, view from board");) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("bno"));
        board.setContents(rs.getString("cont"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("view"));
        list.add(board);
      }

      return list;

    }catch (Exception e) {
      throw e;
    }
  }

  public Board findByNo(int no) throws Exception{
    DriverManager.registerDriver(new Driver());

    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select bno, cont, cdt, view, mno, lno"
            + " from board"
            + " where bno=" + no))  {

      if(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("bno"));
        board.setContents(rs.getString("cont"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("view"));
        board.setWriterNo(rs.getInt("mno"));
        board.setLessonNo(rs.getInt("lno"));
        return board;
      } else {
        return null;
      }
    }
  }

  public int insert(Board board) throws Exception{

    DriverManager.registerDriver(new Driver());

    try (
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
        ){
      //SQL을 서버에 전송. select를 제외하고는 모두 executeUpadte 사용.resultSet 노 쓸모
      return stmt.executeUpdate("insert into board (cont, mno, lno)"
          + " values('" + board.getContents() +"',"
          + board.getWriterNo() + ","
          + board.getLessonNo() + ")");

    }
  }

  public int update(Board board) throws Exception {

    DriverManager.registerDriver(new Driver()); 
    int result = 0;
    
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();) {
      result = stmt.executeUpdate("update board set cont='" + board.getContents()
      + "' where bno=" + board.getNo());
    }catch (Exception e) {
      e.printStackTrace();
    } 
    
    return result;
  }

  public int delete(int no) throws Exception {
    
    DriverManager.registerDriver(new Driver()); 
    Connection con = null;
    Statement stmt = null;
    
    try {
      
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
 
      stmt.executeUpdate("delete from board where bno=" +no);
    
    }catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
    return stmt.executeUpdate("delete from board where bno=" +no);
  }
}
