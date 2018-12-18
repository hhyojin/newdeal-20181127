package com.demoweb.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.model.dao.UploadDao;
import com.demoweb.model.dto.UploadFile;

@WebServlet("/upload/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("number") == null) {			
			response.sendRedirect("/demoweb/upload/uploadlist.jsp");
			return;
		}

		int number = Integer.parseInt(request.getParameter("number"));

		UploadDao dao = new UploadDao();
		UploadFile attach = 
			dao.getUploadFileByUploadFileNo(number);

		if (attach == null) {//지정된 번호에 해당하는 첨부파일이 없는 경우
			response.sendRedirect("/demoweb/upload/uploadlist.jsp");
			return;
		}

		/////////////////////////////////////

		String fileName = attach.getSavedFileName();

		//다운로드할 파일이 저장된 디렉토리의  물리 경로 정보를 조회
		String uploadDirectory =
			request.getServletContext().getRealPath("/upload/uploadfiles");

		//파일의 존재 여부를 확인하고 없을 경우 목록 페이지로 이동
		File file = new File(uploadDirectory, fileName);
		if (!file.exists()) {
			response.sendRedirect(
				"/demoweb/upload/uploadlist.jsp");
			return;
		}

		//파일 다운로드 처리
		response.setContentType("application/unknown");
		response.addHeader(
			"Content-Disposition", "Attachment;filename=" + 
			new String(attach.getUserFileName().getBytes("UTF-8"), "ISO-8859-1").replace("+", "%20"));

		BufferedInputStream istream = 
			new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream ostream = 
			new BufferedOutputStream(response.getOutputStream());

		while (true) {
			int data = istream.read();
			if (data != -1)
				ostream.write(data);
			else
				break;
		}

		ostream.flush();
		ostream.close();
		istream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
