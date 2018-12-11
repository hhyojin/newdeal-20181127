package q181211;

import java.util.Scanner;

public class Bubble {

  public static void main(String[] args) {

    int[] arr = new int[10];
    Scanner sc = new Scanner(System.in);   
    int keyboardNo;

    for(int i=0; i<arr.length; i++) {
      System.out.print("숫자를 입력하세요: ");
      keyboardNo = Integer.parseInt(sc.nextLine());
      if(keyboardNo > 10 || keyboardNo < 0) {
        System.out.println("1에서 10 사이의 수를 입력해 주세요.");
      } 
      arr[i] =keyboardNo;
    }

    for(int i=0;i<arr.length;i++) {
      for(int j=i+1;j<arr.length;j++) {
        if( arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }

    for(int i=0;i<arr.length;i++) {
      System.out.println(arr[i]);
    }
  }

}
