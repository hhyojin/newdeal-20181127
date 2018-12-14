package s181214;

public class MyStackGeneric<T> {
  
  private int top;
  private int maxSize;
  private T[] stackArr;

  public MyStackGeneric(int maxSize){ // stack 사이즈를 입력받아 생성
          this.maxSize = maxSize;
          stackArr =(T[]) new Object[maxSize];
          top = -1;
      }

  public boolean empty() { // 스택이 비었는지 확인
      return (top == -1);
      
      
  }

  public boolean full() { //
      return (top == maxSize - 1);
  }

  public void push(T i) {
      if (full()) {
          System.out.println("스택이 가득참");
      } else {
          stackArr[++top] = i;
      }
  }

  public T pop() {
      
      if (empty()) {
          System.out.println("스택이 비어있음");
          return null;
      } else {
          Object outPut = stackArr[top];
          top--;
          return (T)outPut;
      }
  }
}

