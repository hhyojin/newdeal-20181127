package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  static final int LENGTH = 10;
  private Board[] list;
  private int size = 0;
  
  public BoardList() {
    this.list = new  Board[LENGTH];
  }
  
  public BoardList(int initialCapacity) {
    if(initialCapacity>LENGTH)
      this.list = new Board[initialCapacity];
    else
      this.list = new  Board[LENGTH];
  }
  
  public Board[] toArray() { //회원 데이터 배열을 리턴 
    return Arrays.copyOf(list, size);
  }
  
  public void add(Board board) { //회원 데이터를 저장
    if(size >= list.length) {
      int oldLenght = list.length;
      int newCapacity = oldLenght + oldLenght >> 1; //bit 연산자
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size] = board;
    size++;
  }
  
  
}
