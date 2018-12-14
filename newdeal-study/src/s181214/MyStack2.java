package s181214;

public class MyStack2 {

  private int top;
  private int maxSize;
  private Object[] stackArr;

  public MyStack2(int maxSize){ // stack 사이즈를 입력받아 생성
          this.maxSize = maxSize;
          stackArr = new Object[maxSize];
          top = -1;
      }

  public boolean empty() { // 스택이 비었는지 확인
      return (top == -1);
  }

  public boolean full() { //
      return (top == maxSize - 1);
  }

  public void push(Object i) {
      if (full()) {
          System.out.println("스택이 가득참");
      } else {
          stackArr[++top] = i;
      }
  }

  public Object pop() {
      
      if (empty()) {
          System.out.println("스택이 비어있음");
          return null;
      } else {
          Object outPut = stackArr[top];
          top--;
          return outPut;
      }
  }
  
}
