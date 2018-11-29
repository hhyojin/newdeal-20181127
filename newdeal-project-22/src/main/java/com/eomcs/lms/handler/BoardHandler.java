package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  
  public static Scanner keyboard;
  static final int LENGTH = 10;
  Board[] boards = new Board[LENGTH];
  int boardIdx = 0;

  
  //public static Scanner keyboard; 보다 훨씬 자유로움 & 스캐너 객체 강제시킬 수 있음
  //
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  public void listBoard() {
    for (int j = 0; j < this.boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          this.boards[j].no, 
          this.boards[j].contents, 
          this.boards[j].createdDate, 
          this.boards[j].viewCount);  //this 생략해도 되는데 걍 붙임
    }
  }

  public void addBoard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.no = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("내용? ");
    board.contents = keyboard.nextLine();
    
    board.createdDate = new Date(System.currentTimeMillis()); 
    
    board.viewCount = 0;
    
    this.boards[boardIdx] = board;
    this.boardIdx++;
    
    System.out.println("저장하였습니다.");
  }

}
