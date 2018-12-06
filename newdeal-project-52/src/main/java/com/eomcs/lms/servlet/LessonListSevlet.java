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
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.handler.Command;

@WebServlet("/lesson/list")
public class LessonListSevlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  LessonDao lessonDao;


  @Override
  public void init() throws ServletException{
    
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer"); 

    try {
      lessonDao = iocContainer.getBean(LessonDao.class);    
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
   
    try {
      List<Lesson> list = lessonDao.findAll();
     
      response.setContentType("text/HTML;charset=utf-8");
      request.setAttribute("list", list);
      
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/list.jsp");
      rd.include(request, response);
      
      
    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
}
}
