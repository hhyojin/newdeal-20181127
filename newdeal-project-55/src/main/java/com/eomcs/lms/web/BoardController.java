package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

@Controller
@RequestMapping("/board")
public class BoardController  {

  BoardDao boardDao;
  LessonDao lessonDao;

  public BoardController(BoardDao boardDao, LessonDao lessonDao) {
    this.boardDao = boardDao;
    this.lessonDao = lessonDao;
  }
  
  @RequestMapping("list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
    

      List<Board> list = boardDao.findAll();
      request.setAttribute("list", list);
      
      //출력 컨텐트의 타입을 include하는 쪽에서 지정해야 한다.
      response.setContentType("text/HTML;charset=utf-8"); 
      return "/board/list.jsp";
  }

  @RequestMapping("detail")
  public String detail(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  
    
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDao.findByNo(no);
    request.setAttribute("board", board);
    
    response.setContentType("text/HTML;charset=utf-8");
    return "/board/detail.jsp";
  }
  
  @RequestMapping("form")
  public String form(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
    
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    
    List<Map<String, Object>> lessons;
    
    lessons = lessonDao.findByParticipantNo(loginUser.getNo());
    request.setAttribute("lessons", lessons);
    
    response.setContentType("text/html;charset=UTF-8"); //인클루드면 해줘야 함. 전에는 포워드여서 안 함
    return "/board/form.jsp";
  }
  
  @RequestMapping("add")
  public String add(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김


    Board board = new Board();

    Member loginUser = 
        (Member) request.getSession().getAttribute("loginUser");

    board.setContents(request.getParameter("contents"));
    board.setWriterNo(loginUser.getNo());
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    boardDao.insert(board);

    return "redirect:list";
  }


  @RequestMapping("update")
  public String update(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김


      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setContents(request.getParameter("contents"));
      
      boardDao.update(board);

      //데이터를 변경한 후 웹브라우저에게 목록 URL을 다시 요청하라고 응답한다.
      return "redirect:list";  //상대경로임. 절대 경로 받아도 됨
  }


  @RequestMapping("delete")
  public String delete(HttpServletRequest request, HttpServletResponse response)
      throws Exception {  //예외처리는 프론트 컨트롤러에게 넘김
    
    int no = Integer.parseInt(request.getParameter("no"));
    request.setAttribute("count", boardDao.delete(no));
    
    response.setContentType("text/HTML;charset=utf-8");
    return "/board/delete.jsp"; //여기선 상대경로 ㄴㄴ 항상 루트로 시작해
  }
  
}
