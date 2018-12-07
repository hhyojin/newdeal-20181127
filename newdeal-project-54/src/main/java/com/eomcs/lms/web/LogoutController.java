package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {


  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

    request.getSession().invalidate();
    return "redirect:form"; 
    
  }
}
