package com.demoweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.demoweb.model.dto.Member;

public class MemberDao {
	
	private final String dsn = "oracle";
	//private final String dsn = "mysql";
	
	public void insert(Member member) {
		System.out.println(member.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			
			String sql =
				"INSERT INTO member (" + 
				"memberid, passwd, email, usertype, active) " + 
				"VALUES (?, ?, ?, ?, ?)";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getUserType());
			pstmt.setBoolean(5, member.isActive());
			//4. execute
			pstmt.executeUpdate();
			//5. Process Result if Exist (in case select query)
		} catch (Exception ex) {
			ex.printStackTrace();//오류 메시지 로깅
		} finally {
			//6. close
			if (pstmt != null) 
				try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) 
				try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	public ArrayList<Member> getList() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT memberid, email, usertype, active, regdate " + 
				"FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString(1));
				member.setEmail(rs.getString(2));				
				member.setUserType(rs.getString(3));
				member.setActive(rs.getBoolean(4));
				member.setRegDate(rs.getDate(5));
				members.add(member);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return members;	
		
	}
	
	public Member getMemberById(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT memberid, email, usertype, active, regdate " + 
				"FROM member WHERE memberid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString(1));
				member.setEmail(rs.getString(2));				
				member.setUserType(rs.getString(3));
				member.setActive(rs.getBoolean(4));
				member.setRegDate(rs.getDate(5));
				//members.add(member);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return member;	
		
	}
	
	public Member getMemberByIdAndPasswd(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT memberid, email, usertype, active, regdate " + 
				"FROM member WHERE memberid = ? AND passwd = ? AND active = '1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString(1));
				member.setEmail(rs.getString(2));				
				member.setUserType(rs.getString(3));
				member.setActive(rs.getBoolean(4));
				member.setRegDate(rs.getDate(5));
			}
		} catch (Exception ex) {
			member = null;
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return member;			
	}
	
	

}









