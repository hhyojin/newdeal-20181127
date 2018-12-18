package com.demoweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.model.dao.MemberDao;
import com.demoweb.model.dto.Member;

@WebServlet("/membermanager/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		//1. 로그인 여부 확인 -> 로그인 하지 않았으면 로그인 페이지로 이동
		if (session.getAttribute("loginuser") == null) {
			response.sendRedirect(
				"/demoweb/loginform?returnurl=" + request.getRequestURI());
			return;
		}
		//2. 로그인 했으면 관리자 여부 확인 -> 관리자가 아니라면 로그인 페이지로 이동
//		Member member = (Member)session.getAttribute("loginuser");
//		if (!member.getUserType().equals("admin")) {
//			response.sendRedirect(
//				"/demoweb/loginform?returnurl=" + request.getRequestURI());
//			return;
//		}
		
		//3. 데이터베이스에서 데이터 조회
    	MemberDao dao = new MemberDao();
    	ArrayList<Member> members = dao.getList();
    	
    	//4. JSP에서 사용하도록 데이터 저장
    	//   forward 이동이므로 servlet과 jsp가 공유하는 
    	//   request 객체에 데이터 저장
    	request.setAttribute("members", members);
    	
    	//5. JSP로 forward
    	RequestDispatcher dispatcher = 
    		request.getRequestDispatcher(
    			"/WEB-INF/views/membermanager/list.jsp");
    	dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
