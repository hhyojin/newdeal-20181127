package com.eomcs.lms.web;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.handler.Command;

@Component("/board/delete")
public class BoardDeleteController implements PageController{

  BoardDao boardDao;

  public BoardDeleteController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김

      int no = Integer.parseInt(request.getParameter("no"));
      request.setAttribute("count", boardDao.delete(no));
      
      response.setContentType("text/HTML;charset=utf-8");
      return "/board/delete.jsp"; //여기선 상대경로 ㄴㄴ 항상 루트로 시작해

  }

}
