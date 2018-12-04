package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class MariaDBBoardDao implements BoardDao{

  public List<Board> findAll() throws Exception{ //ArrayList로 하면 안 됨. 유연성을 위해 인터페이스로 적어

    DriverManager.registerDriver(new Driver());

    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("select bno, cont, cdt, view from board");
        ResultSet rs = pstmt.executeQuery();) {

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
        PreparedStatement pstmt = con.prepareStatement("select bno, cont, cdt, view, mno, lno"
            + " from board where bno=?") )  {

      pstmt.setInt(1, no);

      try ( ResultSet rs = pstmt.executeQuery()){
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
  }

  public int insert(Board board) throws Exception{

    DriverManager.registerDriver(new Driver());

    try (
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("insert into board (cont, mno, lno)"
            + " values(?, ?, ?)");){

      pstmt.setString(1,  board.getContents());
      pstmt.setInt(2, board.getWriterNo());
      pstmt.setInt(3, board.getLessonNo());

      return pstmt.executeUpdate();
    }
  }

  public int update(Board board) throws Exception {

    DriverManager.registerDriver(new Driver()); 
    int result = 0;

    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        PreparedStatement pstmt = con.prepareStatement("update board set cont=? where bno=?")){

      pstmt.setString(1, board.getContents());
      pstmt.setInt(2, board.getNo());

      result = pstmt.executeUpdate();

    }catch (Exception e) {
      e.printStackTrace();
    } 

    return result;
  }

  public int delete(int no) throws Exception {

    DriverManager.registerDriver(new Driver()); 
    Connection con = null;
    PreparedStatement pstmt = null;
    int result = 0;
    
    try {

      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      pstmt = con.prepareStatement("delete from board where bno=?");
      pstmt.setInt(1, no);
      
      result = pstmt.executeUpdate();

    }catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      //접속을 끊을 때는 접속과 반대로. 다만 각각의 단계에 모두 try catch 해줘야 함
      try {pstmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
    return result;
  }
}
