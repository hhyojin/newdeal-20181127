package com.eomcs.lms.handler;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;

@Component("/board/delete")
public class BoardDeleteCommand implements Command{

  Scanner keyboard;
  BoardDao boardDao;

  public BoardDeleteCommand (Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  public void execute() {

    try {
      System.out.print("게시글 번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      int result = boardDao.delete(no);
      
      if(result > 0) {
        System.out.println("삭제했습니다.");
      } else System.out.println("해당 번호 게시글이 없습니다.");

    }catch (Exception e) {
      e.printStackTrace();
    }
  }

}
