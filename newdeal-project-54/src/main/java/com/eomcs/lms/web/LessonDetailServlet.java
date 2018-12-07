package com.eomcs.lms.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@WebServlet("/lesson/detail")
public class LessonDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  LessonDao lessonDao;

  @Override
  public void init() throws ServletException{  
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");

    this.lessonDao = iocContainer.getBean(LessonDao.class);  
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
  
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Lesson lesson = new Lesson();
      lesson = lessonDao.findByNo(no);

      request.setAttribute("lesson", lesson);
      
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/detail.jsp");
      rd.include(request, response);

    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }

  }
}

