package lotto;

import java.util.Scanner;

public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner key = new Scanner(System.in);
    
    while(true) {
      System.out.print("숫자?");
      int i = Integer.parseInt(key.nextLine());
      
      System.out.println(i % 7);
      
    }
    
  }

}
