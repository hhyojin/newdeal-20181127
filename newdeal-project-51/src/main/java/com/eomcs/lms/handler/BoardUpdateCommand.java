package com.eomcs.lms.handler;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component("/board/update")
public class BoardUpdateCommand implements Command{

  Scanner keyboard;
  BoardDao boardDao;

  public BoardUpdateCommand (Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  public void execute() {

    try {

      System.out.print("게시글 번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      Board board = boardDao.findByNo(no);

      System.out.printf("내용(%s)? \n", board.getContents());
      board.setContents(keyboard.nextLine());
      
      int result = boardDao.update(board);

      if(result >0) {System.out.println("변경했습니다.");
      } else System.out.println("변경에 실패했습니다.");
      
      

    }catch (Exception e) {
      e.printStackTrace();
    }  

  }
}
