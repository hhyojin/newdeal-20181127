package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Lesson;

public class LessonList {

  static final int LENGTH = 10; //나중에 레슨 리스트 여러 개 생길 수 있으니 계속 스태틱으로 둠
  private Lesson[] list;
  private int size = 0;

  public LessonList() {
    this.list = new Lesson[LENGTH]; //this 생략해도 됨
  }

  public LessonList (int initialCapacity) {
    if (initialCapacity > LENGTH)
      this.list = new Lesson[initialCapacity];
    else
      this.list = new Lesson[LENGTH];
  }

  public Lesson[] toArray() {
    return Arrays.copyOf(list, size); //배열 개수만큼만 리턴
  }
  
  public void add(Lesson lesson) {
   
    if(size >= list.length) {
      int oldLenght = list.length;
      int newCapacity = oldLenght + oldLenght >> 1; //bit 연산자
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size] = lesson;
    size++;
    /* list[size++] = lesson;   /list[size]에 lesson 넣고 ++ 실행*/
  }
  
}
