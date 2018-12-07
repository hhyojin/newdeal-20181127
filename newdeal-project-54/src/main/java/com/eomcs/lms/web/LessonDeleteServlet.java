package com.eomcs.lms.web;
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
import com.eomcs.lms.handler.Command;

@WebServlet("/lesson/delete")
public class LessonDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  LessonDao lessonDao;

  @Override
  public void init() {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
   
    this.lessonDao = iocContainer.getBean(LessonDao.class);
  }  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    
    try {
      
      int no = Integer.parseInt(request.getParameter("no"));
      
      request.setAttribute("count", lessonDao.delete(no));
      
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/delete.jsp"); //여기선 상대경로 ㄴㄴ 항상 루트로 시작해
      response.setContentType("text/HTML;charset=utf-8");
      rd.include(request, response); //include는 다시 돌아옴 forward는 안 돌아옴
         
    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
    
  }
}
