package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  MemberDao memberdao;
  
  @Override
  public void init() throws ServletException{  
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");

    this.memberdao = iocContainer.getBean(MemberDao.class);  
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    RequestDispatcher rd = request.getRequestDispatcher("/member/form.jsp"); //여기선 상대경로 ㄴㄴ 항상 루트로 시작해
    rd.forward(request, response); //include는 다시 돌아옴 forward는 안 돌아옴
  }
   
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
  
    try {
      
      Member member = new Member();
      
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));
      
      memberdao.insert(member);
      
      response.sendRedirect("list");
     
    }catch (Exception e) {
      e.printStackTrace();
    }

  }
}
