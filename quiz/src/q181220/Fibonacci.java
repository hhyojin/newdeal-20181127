package q181220;

import java.util.Scanner;

public class Fibonacci {

  /*  피보나치 수는 F(0)=0, F(1)=1 일 때, 1 이상의 n에 대하여 F(n)=F(n-1) + (n-2)가 적용되는 수입니다.
        처음 두 항을 1과 1로 한 후, 그 다음 항부터는 바로 앞의 두개의 항을 더해 만드는 수열을 의미합니다.
    1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 .....

      F(2) = F(0) + F(1) = 0 + 1 = 1
      F(3) = F(1) + F(2) = 1 + 1 = 2
      F(4) = F(2) + F(3) = 1 + 2 = 3
      F(5) = F(3) + F(4) = 2 + 3 = 5
   */

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("몇 번째 피보나치 수를 볼래? ");
    int number = Integer.parseInt(sc.nextLine());
    FibonacciSequence fSequence = new FibonacciSequence();

    //동적 프로그래밍 이용
    for(int i=0;i<6;i++) {
      System.out.println(fSequence.calc(i));
    }

    //재귀함수 이용
    /*FibonacciSequence fSequence = new FibonacciSequence();
    System.out.println(fSequence.fibo(number));*/

    sc.close();
  }

}
