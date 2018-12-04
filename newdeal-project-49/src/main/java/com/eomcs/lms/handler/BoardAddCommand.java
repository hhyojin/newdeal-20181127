package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardAddCommand implements Command{

  Scanner keyboard;
  BoardDao boardDao;

  public BoardAddCommand (Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  public void execute() {

    try {
      Board board = new Board();
      
      System.out.print("내용? ");
       board.setContents(keyboard.nextLine());

      //어차피 sql문으로 만들 거니 걍 문자로 해서 보내
      System.out.print("작성자 번호? ");
      board.setWriterNo(Integer.parseInt(keyboard.nextLine()));
      
      System.out.print("수업 번호? ");
      board.setLessonNo(Integer.parseInt(keyboard.nextLine()));
  
      int result = boardDao.insert(board);
      
      if (result > 0) {
        System.out.println("입력했습니다.");
      } else {System.out.println("입력에 실패했습니다.");}
      
      
     
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

}
