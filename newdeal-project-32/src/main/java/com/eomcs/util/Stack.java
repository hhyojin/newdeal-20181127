package com.eomcs.util;

public class Stack<E> extends LinkedList<E> implements Cloneable { //1. .clone 쓰기 위해 상속 받음

  private int maxSize;
  
  public Stack() {
    maxSize = 100;
  }
  
  public Stack(int maxSize) {
    this.maxSize = maxSize;
  }
  
  @Override  //2. 상속 받고 오버라이드 함
  public Stack<E> clone() {
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      temp.add(get(i));
    }
    return (Stack<E>) temp; //3 리턴타입 지정
  }
  
  public void push(E value) { //stack처럼 동작하는 메소드를 두 개 추가함
    if (size() == maxSize)
      remove(0);
    add(value);
  }
  
  public E pop() {
    return remove(size() - 1);
  }
  
  public Iterator<E> iterator() {
    //this는 스택객체. 그걸 복사해서 줘야 함.
    return new StackIterator<>(this.clone());
  }
  
  //중첩클래스 사용
  class IteratorImpl<T> implements Iterator<T>{
    
    Stack<?> stack; //? 임의의 값 받을 수 있음
    int size;
    int count;
    
    {//인스턴스 블럭
   
    }
    
    @Override
    public boolean hasNext() {
      return this.count < Stack.this.size();
    }

    @Override
    public T next() {
      this.count++;
      return (T)stack.pop();
    }
    
  }
  
  /*
  public static void main(String[] args) throws Exception {
    Stack<String> stack = new Stack<>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    stack.push("eee");
    stack.push("fff");
    
    Stack<String> temp1 = stack.clone();
    while (temp1.size() > 0) {
      System.out.println(temp1.pop());
    }
    System.out.println("----------------------");
    
    Stack<String> temp2 = stack.clone();
    while (temp2.size() > 0) {
      System.out.println(temp2.pop());
    }
  }*/
}
