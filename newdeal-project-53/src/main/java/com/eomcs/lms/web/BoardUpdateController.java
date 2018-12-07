package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component("/board/update")
public class BoardUpdateController implements PageController {
  
  BoardDao boardDao;

  public BoardUpdateController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김


      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setContents(request.getParameter("contents"));
      
      boardDao.update(board);

      //데이터를 변경한 후 웹브라우저에게 목록 URL을 다시 요청하라고 응답한다.
      return "redirect:list";  //상대경로임. 절대 경로 받아도 됨
      


  }
}
