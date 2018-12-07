package com.eomcs.lms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@Component("/lesson/list")
public class LessonListController implements PageController{
  
  LessonDao lessonDao;

  public LessonListController(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  //여기서는 jsp에게 넘길 데이터만 처리
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
       
      List<Lesson> list = lessonDao.findAll();
     
      response.setContentType("text/HTML;charset=utf-8");
      request.setAttribute("list", list);
      
    //출력 컨텐트의 타입을 include하는 쪽에서 지정해야 한다.
      response.setContentType("text/HTML;charset=utf-8"); 
      return "/lesson/list.jsp";

}
}
