package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component("/board/list")
public class BoardListCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao; //try 안에서 만들면 계속해서 가비지를 만듦. 그러니 주입 받는다
  
  public BoardListCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  public void execute() {
    try {
      List<Board> list = boardDao.findAll();
      
      for (Board board : list) {
        System.out.printf("%3d, %-20s, %s, %d\n", 
            board.getNo(), 
            board.getContents(), 
            board.getCreatedDate(), 
            board.getViewCount());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


