package q181211;


public class starCreate {

  /*
   *입력받은 정수값을 다른클래스에 전달해준뒤 \
   *입력한 별의 갯수만큼 별을 쌓아주시면 됩니다.
   *조건은 별짓기를 할때 사용할 제어문은 for문 2개만 사용해서 만들수있습니다.
   *(if,switch등 다른제어문 사용X) 
   */

  public void starCreate(int keyboardNo) {

    for(int i =1; i < keyboardNo+1 ; i++) {
      for(int j = 0; j<i; j++) {
        System.out.print("*");
      }
      System.out.println("");
    }
  }
}


