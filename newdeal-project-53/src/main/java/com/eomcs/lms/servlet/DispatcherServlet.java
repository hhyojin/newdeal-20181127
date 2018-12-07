package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.web.PageController;

@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  ApplicationContext iocContainer;

  @Override
  public void init() {
    iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
  }

  @Override  //service는 get과 post 다 받음
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //클라이언트가 요청한 명령을 추출하기
    // -> /app/board/list  ----> /board/list
    String controllerPath = request.getPathInfo();

    try {
      //클라이언트가 요청을 처리할 객체를 Spring IoC Container에서 찾는다.
      PageController controller = (PageController) iocContainer.getBean(controllerPath);

      //페이지 컨트롤러의 메소드를 호출
      String viewUrl = controller.execute(request, response);
    //페이지 컨트롤러가 알려준 jsp를 inclde한다
      if(viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.replace("redirect:", ""));
      } else {
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl); //여기선 상대경로 ㄴㄴ 항상 루트로 시작해
        rd.include(request, response); //include는 다시 돌아옴 forward는 안 돌아옴
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
    //클라이언트가 요청한 주소 보고 그 페이지 컨트롤러에게 넘기고 응답을 페이지로 넘김
    //공통 코드 사라져서 코드가 간결해짐
  }

}
