package q181220;

import java.util.Scanner;

public class Fibonacci {

/*  피보나치 수는 F(0)=0, F(1)=1 일 때, 1 이상의 n에 대하여 F(n)=F(n-1) + (n-2)가 적용되는 수입니다.
  
      F(2) = F(0) + F(1) = 0 + 1 = 1
      F(3) = F(1) + F(2) = 1 + 1 = 2
      F(4) = F(2) + F(3) = 1 + 2 = 3
      F(5) = F(3) + F(4) = 2 + 3 = 5

      피보나치 수는 0번째부터 0, 1, 1, 2, 3, 5...와 같이 이어집니다.*/
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("몇 번째 피보나치 수를 볼래? ");
    int number = Integer.parseInt(sc.nextLine());
    FibonacciSequence fSequence = new FibonacciSequence();
    
    fSequence.calc(number);
    
    sc.close();
  }

}
