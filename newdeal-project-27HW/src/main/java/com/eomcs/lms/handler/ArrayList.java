package com.eomcs.lms.handler;

import java.util.Arrays;

public class ArrayList<T> {
  final int DEFAULT_CAPACITY = 10;
  Object[] list;
  int size = 0;
  
  public ArrayList() {
    list  = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }
  
  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if(a.length < size) {
      return (T[]) Arrays.copyOf(list, size, a.getClass());
    //a.getClass() 배열의 뭔 정보를 넘겨준다는데 시바
      //class [Lcom.eomcs.lms.domain.Board; 이거 찍히는데 으잉
      
    //Arrays.copyOf(original, newLength, newType)
      //original: 카피할 배열
      //newLength: 몇 번째까지 복사할지
      //newType: 새로운 배열의 길이가 원본 배열보다 길면, 나머지 요소는 배열 요소의 타입에 맞게
              //기본값으로 채워집니다.
    }
    
  //list의 0번째 방부터 a의 0번째 방부터 size만큼 배열 복사
    System.arraycopy(list, 0, a, 0, size);
    
    if (a.length > size)
      a[size] = null; //파라미터로 받은 배열의 길이가 사이즈보다 길면 배열의 마지막임을 알려주기 위해 null을 넣어줌
    
    return a; 
  }
  
  public void add(T item) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }
    
    list[size++] = item;
  }
  
  public int size() {
    return this.size;
  }
  
}
