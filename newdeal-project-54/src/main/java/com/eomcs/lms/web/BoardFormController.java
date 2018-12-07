package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Member;

@Controller
public class BoardFormController  {
  
  LessonDao lessonDao;
  
  public BoardFormController(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @RequestMapping("/board/form")
  public String form(HttpServletRequest request, HttpServletResponse response)
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
