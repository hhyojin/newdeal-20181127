package com.eomcs.lms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

//이 클래스의 객체는 Spring IoC 컨테이너가 관리하도록 설정한다
@Controller
public class BoardListController {
  
  BoardDao boardDao;

  public BoardListController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  //여기서는 jsp에게 넘길 데이터만 처리
  @RequestMapping("/board/list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
    

      List<Board> list = boardDao.findAll();
      request.setAttribute("list", list);
      
      //출력 컨텐트의 타입을 include하는 쪽에서 지정해야 한다.
      response.setContentType("text/HTML;charset=utf-8"); 
      return "/board/list.jsp";

  }
}
