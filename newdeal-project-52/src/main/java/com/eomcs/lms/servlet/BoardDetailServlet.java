package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");

    this.boardDao = iocContainer.getBean(BoardDao.class);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardDao.findByNo(no);

      request.setAttribute("board", board);


      //jsp로 실행을 위임한다
      RequestDispatcher rd = request.getRequestDispatcher("/board/detail.jsp");
      /*response.setContentType("text/HTML;charset=utf-8");*/
      rd.include(request, response); //include는 다시 돌아옴 forward는 안 돌아옴
      //include의 경우는 include하는 쪽에서 contentType 설정해 줘야 함;

    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}


