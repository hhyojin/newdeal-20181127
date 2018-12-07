package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.handler.Command;

@WebServlet("/member/delete")
public class MemberDeleteCommand extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  MemberDao memberdao;
  
  @Override
  public void init() {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
   
    this.memberdao = iocContainer.getBean(MemberDao.class);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

   try {
      
      int no = Integer.parseInt(request.getParameter("no"));
      
      int count = memberdao.delete(no);   
      request.setAttribute("count", count);
      
      RequestDispatcher rd = request.getRequestDispatcher("/member/delete.jsp"); //여기선 상대경로 ㄴㄴ 항상 루트로 시작해
      response.setContentType("text/HTML;charset=utf-8");
      rd.include(request, response);
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
