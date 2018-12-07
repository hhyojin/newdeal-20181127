package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {
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
      int no = Integer.parseInt(request.getParameter("no"));
      
      Member member = new Member();
      member = memberdao.findByNo(no);
      
      request.setAttribute("member", member);
      
      RequestDispatcher rd = request.getRequestDispatcher("/member/detail.jsp");
      rd.include(request, response);
      
    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}
