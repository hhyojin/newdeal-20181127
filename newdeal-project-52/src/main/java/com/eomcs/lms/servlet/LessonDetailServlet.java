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
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@WebServlet("/lesson/detail")
public class LessonDetailServlet extends HttpServlet {
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
    System.out.println("init()");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
   
    System.out.println("1. 서블릿");
    
    
    try {
      System.out.println("2. try");
      int no = Integer.parseInt(request.getParameter("no"));
      System.out.println("3. no: " + no);
      Lesson lesson = new Lesson();
      lesson = lessonDao.findByNo(no);
      System.out.println("4. dao: " + lesson);
      System.out.println("5. dao: " +lesson.getTitle());

      request.setAttribute("lesson", lesson);
      
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/detail.jsp");
      rd.include(request, response);

    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }

  }
}

