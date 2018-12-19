package q181217;

import java.util.Scanner;

public class PerfectNumber {

  public static void main(String[] args) {
    //자연수 n 받고 그 이하의 모든 완전수를 구하라

    Scanner sc = new Scanner(System.in);

    System.out.print("자연수를 입력하세요. ");
    int inputNo = Integer.parseInt((sc.nextLine()));

    for(int i=1;i<=inputNo;i++) {
      int sum=0;
      for(int j=1;j<i;j++) {
        if(i%j==0) {
          sum += j; //i의 약수 j를 더함
        }
      } //i의 약수를 전부 구하고 sum과 비교
      if(i == sum) {
        System.out.println(i);
      }
    }
  }    
}
