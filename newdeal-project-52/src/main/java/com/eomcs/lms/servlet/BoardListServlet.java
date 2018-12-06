package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;


//서블릿을 만들었으면 톰캣 서버에 알려줘야 함
// -> 서블릿에 URL을 부여한다
// -> URL은 항상 '/'로 시작해야 한다
// -> 서블릿을 추가했으면 톰캣 서버를 재시작해야한다
// -> 한 번 톰캣 서버에 서블릿을 추가한 후 서블릿을 변경한다면 일정 시간이 지난 후에 자동으로 해당 서블릿을 재적재(리로드)함
//     즉, 서버를 재시작할 필요가 없다(설정에 오토 리로드 추가한 경우만)
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  BoardDao boardDao;

  @Override
  public void init() throws ServletException{
    //이 메소드는 서블릿 객체가 최초로 생성될 때 생성자 다음에 바로 호출된다
    //원래는 init(ServletConfig)가 먼저 호출되고, init(ServletConfig)가 이 init()를 호출한다

    //BoardDao 객체를 꺼내기 위해 먼저 IoC 컨테이너를 꺼낸다
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer"); 

    try {
      boardDao = iocContainer.getBean(BoardDao.class);    
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  //클라이언트 요청이 올 때마다 호출
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 

    try {
      List<Board> list = boardDao.findAll();

      //출력 컨텐트의 타입을 include하는 쪽에서 지정해야 한다.
      response.setContentType("text/HTML;charset=utf-8");
      
      //jsp가 게시물 목록을 사용할 수 있도록 보관소에 저장
      request.setAttribute("list", list);
     
      //jsp로 실행을 위임한다
      RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
      rd.include(request, response); //include는 다시 돌아옴 forward는 안 돌아옴
      //include의 경우는 include하는 쪽에서 contentType 설정해 줘야 함
      
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }

  }
}
