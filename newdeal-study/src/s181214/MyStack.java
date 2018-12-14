package s181214;

public class MyStack {
  
  //Stack 자료 구조를 설명하고 아래 코드를 완성 하세요

   private static Object[] stackArr;
   private static int pointer;
   
   public MyStack() {
     stackArr = new Object[5];
   }
   
   
   public static void empty() { // 스택이 비었는지 확인
     System.out.println("empty pointer" + pointer);
    if(pointer == -1) {
      System.out.println("스택이 비었습니다");
    }
    System.out.println("스택에 내용이 있습니다");
   }
   
   public static void full() { //스택이 풀인지 확인
    if(pointer == stackArr.length) {
      System.out.println("스택이 꽉 찼습니다");
    }
    System.out.println("스택에 여유가 있습니다");
   }
   
   public void push(Object obj) {
     if(pointer == stackArr.length) {
       System.out.println("스택이 꽉 찼습니다");
       return;
     }
     stackArr[pointer++] = obj;
     System.out.println("스택에 push, pointer: " + pointer);
   }
   
   public Object pop(Object obj) {     
     if(pointer == 0) {
       System.out.println("스택이 비었습니다.");
       return null;
     }
     return stackArr[--pointer];
   }
   
   public static void main(String[] args) {
     MyStack ms = new MyStack();
     for(int i=0;i<6;i++){
       ms.push(i);
     }
     
     full();
     
     for(int i=0;i<6;i++){
       ms.pop(i);
       System.out.println("pop pointer: " + pointer);
     }

     empty();
     
   }
   
 }
