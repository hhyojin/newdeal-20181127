package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.Command;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  MemberDao memberdao;
  
  @Override
  public void init() throws ServletException{
    
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer"); 

    try {
      memberdao = iocContainer.getBean(MemberDao.class);    
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
   
    try {
      List<Member> list = memberdao.findAll();
      
      response.setContentType("text/HTML;charset=utf-8");
      request.setAttribute("list", list);

      RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
      rd.include(request, response);
      
    }catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
