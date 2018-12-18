package com.demoweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.demoweb.model.dto.Board;

public class BoardDao {
	
	public void insertBoard(Board board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. 드라이버 등록
			//2. 연결객체생성 (DriverManager에서 반환)
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 + 명령객체 생성 (from connection)
			String sql = 
				"INSERT INTO Board " +
				"(boardno, title, writer, content, groupno, step)" +
				"VALUES (board_sequence.nextval, ?, ?, ?, " +
				"board_sequence.currval, 1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
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
	
	public ArrayList<Board> getBoardList(int start, int last) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;//조회 결과에 접근하는 참조 변수
		//데이터베이스의 데이터를 읽어서 저장할 객체 컬렉션
		ArrayList<Board> boards = new ArrayList<Board>();
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			StringBuffer sql = new StringBuffer(500);
			sql.append("SELECT * ");
			sql.append("FROM ");
			
			sql.append("( ");
			sql.append("	SELECT "); 
			sql.append("		rownum idx, s.* "); 
			sql.append("	FROM ");
			
			sql.append("	( ");
			sql.append("		SELECT "); 
			sql.append("			boardno, title, writer, ");
			sql.append("			regdate, readcount, ");
			sql.append("			deleted, groupno, step, depth ");
			sql.append("		FROM ");
			sql.append("			board ");
			sql.append("		ORDER BY groupno DESC, step ASC ");
			sql.append("	) s ");
			
			sql.append(") ");
			
			sql.append("WHERE idx >= ? AND idx < ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt(2));
				board.setTitle(rs.getString(3));
				board.setWriter(rs.getString(4));
				board.setRegDate(rs.getDate(5));
				board.setReadCount(rs.getInt(6));
				board.setDeleted(rs.getBoolean(7));
				board.setGroupNo(rs.getInt(8));
				board.setStep(rs.getInt(9));
				board.setDepth(rs.getInt(10));
				
				boards.add(board);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결닫기
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}
	//게시물 총 건수를 조회, 반환
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "SELECT COUNT(*) FROM board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			
		} catch (Exception ex) {
			count = 0;
			ex.printStackTrace();			
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception ex) { }
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return count;
	}
	
	public Board getBoardByBoardNo(int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			StringBuffer sql = new StringBuffer(300);
			sql.append("SELECT "); 
			sql.append("boardno, title, writer, content, ");
			sql.append("regdate, readcount, ");
			sql.append("deleted, groupno, step, depth ");
			sql.append("FROM board ");
			sql.append("WHERE boardno = ? AND deleted = '0'");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setRegDate(rs.getDate(5));
				board.setReadCount(rs.getInt(6));
				board.setDeleted(rs.getBoolean(7));
				board.setGroupNo(rs.getInt(8));
				board.setStep(rs.getInt(9));
				board.setDepth(rs.getInt(10));
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception ex) { }
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return board;
	}
	
	public int deleteBoard(int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql =
				"UPDATE board " +
				"SET deleted = '1' " + 
				"WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			count = pstmt.executeUpdate();
		} catch (Exception ex) {
			count = 0;
			ex.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return count;
	}
	
	public void increaseReadCount(int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = 
				"UPDATE board " +
				"SET readcount = readcount + 1 " + 
				"WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
	}
	
	public int updateBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = 
				"UPDATE board " +
				"SET title = ?, content = ? " + 
				"WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			count = pstmt.executeUpdate();
		} catch (Exception ex) {
			count = 0;
			ex.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return count;
	}
	
	public int insertReply(Board board) {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			//댓글을 작성할 대상글의 정보를 조회
			Board temp = 
				getBoardByBoardNo(board.getBoardNo());
			
			//영향을 받는 기존 글들의 논리적인 순서 번호 변경
			conn = ConnectionHelper.getConnection("oracle");			
			String sql =
				"UPDATE board " + 
				"SET step = step + 1 " + 
				"WHERE step > ? AND groupno = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, temp.getStep());//step 번호
			pstmt.setInt(2, temp.getGroupNo());//group 번호
			pstmt.executeUpdate();
			pstmt.close();
			
			//댓글 삽입
			sql = 				
				"INSERT INTO board " +
				"(boardno, title, writer, content, " + 
				"deleted, groupno, step, depth) " +
				"VALUES (board_sequence.nextVal, ?, ?, ?, '0', " + 
				"?, ?, ?)";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, board.getTitle());//제목
			pstmt.setString(2, board.getWriter());//작성자
			pstmt.setString(3, board.getContent());//내용
			pstmt.setInt(4, temp.getGroupNo());//step
			pstmt.setInt(5, temp.getStep() + 1);//step
			pstmt.setInt(6, temp.getDepth() + 1);//depth
			
			count = pstmt.executeUpdate();
		} catch (Exception ex) {
			count = 0;
			ex.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return count;
		
	}

}







