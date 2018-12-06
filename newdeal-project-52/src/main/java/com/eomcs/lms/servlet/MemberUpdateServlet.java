package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.Scanner;
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

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  MemberDao memberdao;
  
  @Override
  public void init() {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
   
    this.memberdao = iocContainer.getBean(MemberDao.class);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      
      Member loginUser = 
          (Member) request.getSession().getAttribute("loginUser");
      
      int no = loginUser.getNo();
      
      Member member = new Member();
      member = memberdao.findByNo(no);
      
      member.setNo(Integer.parseInt(request.getParameter("no"))); 
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));
      
      memberdao.update(member);
      
      response.sendRedirect("list");
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  
  }
}
