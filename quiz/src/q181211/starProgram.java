package q181211;

import java.util.Scanner;

public class starProgram {

  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);   
    System.out.print("별 개수 입력: ");
    int keyboardNo = Integer.parseInt(sc.nextLine());

    starCreate star = new starCreate();
    star.starCreate(keyboardNo);

  }

}
