package com.eomcs.lms.web;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component("/auth/login")
public class LoginController implements PageController{

  MemberDao memberDao;
  
  public LoginController (MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김


    request.setCharacterEncoding("UTF-8");

      HashMap<String, Object> params = new HashMap<>();
      
      params.put("email", request.getParameter("email"));
      params.put("password", request.getParameter("password"));
      
      Member member = memberDao.findByEmailPassword(params);
      
      //해당 클라이언트를 위한 HttpSession 객체가 있다면
      //그 객체를 리턴
      //없다면 새로 만들어 리턴
      HttpSession session = request.getSession();
      
      if (member != null) {
        session.setAttribute("loginUser", member);
        return "redirect:../board/list";
      } else {
        session.invalidate();
        return "redirect:form";   
      }
  }

}
