package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;

@Component("/auth/form")
public class LoginFormController implements PageController{

  MemberDao memberDao;
  
  public LoginFormController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
     
    response.setContentType("text/html;charset=UTF-8");
    return "/auth/form.jsp";
  }
    
  }


