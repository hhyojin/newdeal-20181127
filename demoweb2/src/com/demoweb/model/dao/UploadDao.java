package com.demoweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.demoweb.model.dto.Upload;
import com.demoweb.model.dto.UploadFile;

public class UploadDao {

	private final String dsn = "oracle";
	//private final String dsn = "mysql";
	
	public int insertUpload(Upload upload) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int uploadNumber = -1;
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"INSERT INTO upload " + 
				"(uploadno, title, uploader, content) " +
				"VALUES (upload_sequence.nextVal, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upload.getTitle());
			pstmt.setString(2, upload.getUploader());
			pstmt.setString(3, upload.getContent());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("SELECT upload_sequence.currVal FROM DUAL");
		    rs = pstmt.executeQuery();
		    if (rs.next())
		    	uploadNumber = rs.getInt(1);
		} catch (Exception ex) {
			uploadNumber = -1;
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) { }
			try { pstmt.close(); } catch (Exception ex) { }
			try { conn.close(); } catch (Exception ex) { }
		}
		
		return uploadNumber;
	}
	
	public void insertUploadFile(UploadFile file) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"INSERT INTO uploadfile " + 
				"(uploadfileno, uploadno, savedfilename, userfilename) " +
				"VALUES (uploadfile_sequence.nextVal, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, file.getUploadNo());
			pstmt.setString(2, file.getSavedFileName());
			pstmt.setString(3, file.getUserFileName());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception ex) { }
			try { conn.close(); } catch (Exception ex) { }
		}		
		
	}
	
	public ArrayList<Upload> getUploadList() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Upload> uploads = new ArrayList<Upload>();
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = "SELECT uploadno, title, regdate from upload where deleted = '0'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Upload upload = new Upload();
				upload.setUploadNo(rs.getInt(1));
				upload.setTitle(rs.getString(2));
				upload.setRegDate(rs.getDate(3));
				uploads.add(upload);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally { 
			try { rs.close(); } catch (Exception ex) { }
			try { pstmt.close(); } catch (Exception ex) { }
			try { conn.close(); } catch (Exception ex) { }
		}
		
		return uploads;
				
	}
	
	public Upload getUploadByUploadNo(int uploadNo) {
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;;
		ResultSet rs = null, rs2 = null;
		Upload upload = null;
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT uploadno,title,uploader, " + 
				"content, regdate " +
				"FROM upload " + 
				"WHERE uploadno = ? AND deleted = '0'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uploadNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				upload = new Upload();
				upload.setUploadNo(uploadNo);
				upload.setTitle(rs.getString(2));
				upload.setUploader(rs.getString(3));
				upload.setContent(rs.getString(4));
				upload.setRegDate(rs.getDate(5));
				
				sql = 
					"SELECT uploadfileno, uploadno, savedfilename, userfilename " + 
					"FROM uploadfile WHERE uploadno = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, uploadNo);
				rs2 = pstmt2.executeQuery();
				ArrayList<UploadFile> files = new ArrayList<UploadFile>();
				while (rs2.next()) {
					UploadFile file = new UploadFile();
					file.setUploadFileNo(rs2.getInt(1));
					file.setUploadNo(rs2.getInt(2));
					file.setSavedFileName(rs2.getString(3));
					file.setUserFileName(rs2.getString(4));
					files.add(file);					
				}
				upload.setFiles(files);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs2.close(); } catch (Exception ex) {}
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt2.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return upload;
		
	}
	
	public ArrayList<UploadFile> getUploadFilesByUploadNo(int uploadNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UploadFile> files = new ArrayList<UploadFile>();
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT uploadfileno, uploadno, savedfilename, userfilename " + 
				"FROM uploadfile WHERE uploadfileno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uploadNo);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				UploadFile file = new UploadFile();
				file.setUploadFileNo(rs.getInt(1));
				file.setUploadNo(rs.getInt(2));
				file.setSavedFileName(rs.getString(3));
				file.setUserFileName(rs.getString(4));
				files.add(file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return files;
		
	}
	
	public UploadFile getUploadFileByUploadFileNo(int uploadFileNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UploadFile file = null;
		
		try {
			conn = ConnectionHelper.getConnection(dsn);
			String sql = 
				"SELECT uploadfileno, uploadno, savedfilename, userfilename " + 
				"FROM uploadfile WHERE uploadfileno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uploadFileNo);
			rs = pstmt.executeQuery();
				
			if (rs.next()) {
				file = new UploadFile();
				file.setUploadFileNo(rs.getInt(1));
				file.setUploadNo(rs.getInt(2));
				file.setSavedFileName(rs.getString(3));
				file.setUserFileName(rs.getString(4));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return file;
		
	}
	
}
























