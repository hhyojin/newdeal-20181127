package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDao;

@Controller 
public class BoardDeleteController {

  BoardDao boardDao;

  public BoardDeleteController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping("/board/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

      int no = Integer.parseInt(request.getParameter("no"));
      request.setAttribute("count", boardDao.delete(no));
      
      response.setContentType("text/HTML;charset=utf-8");
      return "/board/delete.jsp"; //여기선 상대경로 ㄴㄴ 항상 루트로 시작해

  }

}
