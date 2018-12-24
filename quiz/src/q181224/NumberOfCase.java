package q181224;

import java.util.Scanner;

public class NumberOfCase {

  //문제. 로봇이 1m 또는 2m 씩 걸어갈 수 있다. 로봇이 (n)m 를 갈 수 있는 모든 경우의 수를 구하시오.

        public static void main(String[] args) { 
          Scanner sc = new Scanner(System.in);
          System.out.println("로봇이 걸어갈 거리를 입력해주세요.>>");
          int n = Integer.parseInt(sc.nextLine());
          System.out.println("경우의 수 " + walk(n)+" 개"); 
       } 
}
