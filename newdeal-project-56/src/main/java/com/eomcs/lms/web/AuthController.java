package com.eomcs.lms.web;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Controller
@RequestMapping("/auth")
public class AuthController {

  MemberDao memberDao;

  public AuthController (MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @RequestMapping("login")
  public String login(String email, String password, HttpSession session)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

    HashMap<String, Object> params = new HashMap<>();

    params.put("email", email);
    params.put("password", password);

    Member member = memberDao.findByEmailPassword(params);
   
    if (member != null) {
      session.setAttribute("loginUser", member);
      return "redirect:../board/list";
    } else {
      session.invalidate();
      return "redirect:form";   
    }
  }

  @RequestMapping("form")
  public String loginForm(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

    return "auth/form";
  }

  @RequestMapping("logout")
  public String logout(HttpSession session)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

    session.invalidate();
    return "redirect:form"; 

  }


}
