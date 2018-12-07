package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;

@Controller
public class LoginFormController {

  MemberDao memberDao;
  
  public LoginFormController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/auth/form")
  public String loginForm(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
     
    response.setContentType("text/html;charset=UTF-8");
    return "/auth/form.jsp";
  }
    
  }


