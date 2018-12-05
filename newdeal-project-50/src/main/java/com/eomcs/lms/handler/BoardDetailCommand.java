package com.eomcs.lms.handler;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component("/board/detail")
public class BoardDetailCommand implements Command{

  Scanner keyboard;
  BoardDao boardDao;

  public BoardDetailCommand (Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  public void execute() {
    try {
      
      System.out.print("수업번호?");
      int no = Integer.parseInt(keyboard.nextLine());
      
      Board board = new Board();
      board = boardDao.findByNo(no);

      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("등록일: %s\n", board.getCreatedDate());
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.printf("회원번호: %d\n", board.getWriterNo());
      System.out.printf("강의번호: %d\n", board.getLessonNo());
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}


