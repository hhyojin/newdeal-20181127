package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.model.dao.MemberDao;
import com.demoweb.model.dto.Member;

@WebServlet("/membermanager/view")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 목록 페이지에서 전달한 사용자아이디를 읽어서 변수에 저장
		String memberId = request.getParameter("memberid");
		//2. memberId가 전달되지 않았다면 목록으로 이동
		if (memberId == null || memberId.length() == 0) {
			response.sendRedirect("list");
			return;
		}
		//3. 사용자아이디로 데이터 조회
		MemberDao dao = new MemberDao();
		Member member = dao.getMemberById(memberId);
		if (member == null) {//조회 실패
			response.sendRedirect("list");
			return;
		}
		
		//4. jsp에서 사용할 수 있도록 데이터 공유 객체에 저장
		request.setAttribute("member", member);
		
		//5. jsp로 이동 (forward)
		RequestDispatcher dispatcher = 
    		request.getRequestDispatcher(
    			"/WEB-INF/views/membermanager/view.jsp");
    	dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
