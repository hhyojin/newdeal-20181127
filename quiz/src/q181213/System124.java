package q181213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class System124 {

  private static int no;
  private static String result;
  private static int quota;
  private static int rest;
  private static List<Integer> arr = new ArrayList<>();
    
  public static void main(String[] args) {
   
    Scanner sc = new Scanner(System.in); 
        
    System.out.print("0~ 500,000,000 사이의 자연수를 입력해 주세요.");
    no = Integer.parseInt(sc.nextLine());
    
    if (no < 0 | no > 500000000) {
      System.out.println("잘못된 자연수입니다.");      
    } else  {
  
      do {
        quota = no/3;
        rest = no%3;
        arr.add(rest);
        System.out.println("quota: " + quota);
        System.out.println("rest: " + rest);
      } while(quota > 3);
              
      arr.add(quota);
      System.out.println(arr.size());
      
      for(int i=arr.size(); i < 0;i--) {
        System.out.println("똑똑");
        System.out.println("아 저기요 --;" + Integer.toString(arr.get(i)));
        result += Integer.toString(arr.get(i));
      }
      
      System.out.println("result: " + result);
    }

   
    

  }

}
