package com.demoweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.demoweb.model.dto.BoardComment;

public class CommentDao {

  public void updateComment(BoardComment boardComment) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
      //1. 드라이버 등록
      //2. 연결객체생성 (DriverManager에서 반환)
      conn = ConnectionHelper.getConnection("oracle");
      //3. SQL 작성 + 명령객체 생성 (from connection)
      String sql = 
          "update boardcomment set content=? where commentno=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, boardComment.getContent());
      pstmt.setInt(2, boardComment.getCommentNo());
      //4. 명령 실행
      pstmt.executeUpdate();
      //5. 결과 있으면 결과 처리
      
  } catch (Exception ex) {
      ex.printStackTrace();
  } finally {
      //6. 연결닫기
      if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
      if (conn != null) try { conn.close(); } catch (Exception ex) {}
  }
  }
  
  
  
  
  public void deleteComment(int commentNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
      //1. 드라이버 등록
      //2. 연결객체생성 (DriverManager에서 반환)
      conn = ConnectionHelper.getConnection("oracle");
      //3. SQL 작성 + 명령객체 생성 (from connection)
      String sql = 
          "delete boardcomment where commentno=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, commentNo);
      //4. 명령 실행
      pstmt.executeUpdate();
      //5. 결과 있으면 결과 처리
      
  } catch (Exception ex) {
      ex.printStackTrace();
  } finally {
      //6. 연결닫기
      if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
      if (conn != null) try { conn.close(); } catch (Exception ex) {}
  }
  }
  
  
  
  public void insertComment(BoardComment boardComment) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
      //1. 드라이버 등록
      //2. 연결객체생성 (DriverManager에서 반환)
      conn = ConnectionHelper.getConnection("oracle");
      //3. SQL 작성 + 명령객체 생성 (from connection)
      String sql = 
          "INSERT INTO BOARDCOMMENT " +
          "(COMMENTNO, BOARDNO, writer, content)" +
          "VALUES (boardcomment_sequence.nextval, ?, ?, ?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, boardComment.getBoardNo());
      pstmt.setString(2, boardComment.getWriter());
      pstmt.setString(3, boardComment.getContent());
      //4. 명령 실행
      pstmt.executeUpdate();
      //5. 결과 있으면 결과 처리
      
  } catch (Exception ex) {
      ex.printStackTrace();
  } finally {
      //6. 연결닫기
      if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
      if (conn != null) try { conn.close(); } catch (Exception ex) {}
  }
  }
  
  public List<BoardComment> getCommentList(int boardNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    
    List<BoardComment> commentlist = new ArrayList<BoardComment>();
    
    try {
      //1. 드라이버 등록
      //2. 연결객체생성 (DriverManager에서 반환)
      conn = ConnectionHelper.getConnection("oracle");
      //3. SQL 작성 + 명령객체 생성 (from connection)
      String sql =       
      "SELECT commentno, writer, content, TO_CHAR(regdate, 'yyyy-mm-dd') regdate from boardcomment where boardno = ? order by commentno desc";  
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, boardNo);
      //4. 명령 실행
      rs = pstmt.executeQuery();
      //5. 결과 있으면 결과 처리
      while (rs.next()) 
      {
        BoardComment boardComment = new BoardComment();
        boardComment.setCommentNo(rs.getInt("commentNo"));
        boardComment.setWriter(rs.getString("writer"));
        boardComment.setContent(rs.getString("content"));
        boardComment.setRegDate2(rs.getString("regdate"));
        commentlist.add(boardComment);
      }
      
  } catch (Exception ex) {
      ex.printStackTrace();
  } finally {
      //6. 연결닫기
      if (rs != null) try { rs.close(); } catch (Exception ex) {}
      if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
      if (conn != null) try { conn.close(); } catch (Exception ex) {}
  }
    
    return commentlist;
    
  }
  
  
  
  
}
