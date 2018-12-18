package com.demoweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.model.dao.MemberDao;
import com.demoweb.model.dto.Member;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 처리
		String id = request.getParameter("memberId");
		String passwd = request.getParameter("passwd");

		MemberDao dao = new MemberDao();
		Member member = dao.getMemberByIdAndPasswd(id, passwd);
		
		//결과에 따라 이동 (다른 서블릿으로 이동할 경우 redirect)
		if (member == null) {
			String url = "/demoweb/loginform?loginid=" + id;
			response.sendRedirect(url);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginuser", member);
	
			String returnUrl = request.getParameter("returnurl");
			if (returnUrl == null || returnUrl.length() == 0)
				response.sendRedirect("/demoweb/home");
			else
				response.sendRedirect(returnUrl);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}








