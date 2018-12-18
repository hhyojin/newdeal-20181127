package com.demoweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.model.dao.MemberDao;
import com.demoweb.model.dto.Member;

@WebServlet("/membermanager/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1-1. 사용자가 입력해서 전송한 데이터를 읽어서 변수에 저장

		String memberId = request.getParameter("memberId");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String userType = request.getParameter("userType");
		String active = request.getParameter("active");

		//1-2.DTO 객체를 생성하고 데이터를 저장
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		member.setEmail(email);
		member.setUserType( userType.equals("u") ? "user" : "admin" );
		member.setActive( active == null ? false : true );

		//2. 데이터베이스에 저장
		MemberDao dao = new MemberDao();
		dao.insert(member);

		//3. 목록페이지로 이동
		//sendRedirect : 지정된 경로로 이동 (재요청)
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
