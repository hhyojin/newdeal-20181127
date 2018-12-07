package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Member;

@Component("/board/form")
public class BoardFormController implements PageController {
  
  LessonDao lessonDao;
  
  public BoardFormController(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
       
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    
    List<Map<String, Object>> lessons;
    
      lessons = lessonDao.findByParticipantNo(loginUser.getNo());
      request.setAttribute("lessons", lessons);
      
      response.setContentType("text/html;charset=UTF-8"); //인클루드면 해줘야 함. 전에는 포워드여서 안 함
      return "/board/form.jsp";
  }
  
}
