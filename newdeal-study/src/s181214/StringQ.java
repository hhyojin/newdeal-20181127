package s181214;

import java.util.Scanner;

public class StringQ {

  private static String regNo;
  private static int sortSex;
  private static Scanner sc = new Scanner(System.in);
  
//1. 자리수 체크 (기능)함수 (14 ok)
  private static void checkNo(String regNo) {
    while(regNo.length() != 14) {
      System.out.println("올바른 형식으로 입력해 주세요. ");
      System.out.println("예) 111111-1111111");
      System.out.print("주민등록번호를 입력해 주세요. ");
      regNo = sc.nextLine();
    } 
    checkAfterNo(regNo);
  }
  
//2. 뒷번호 첫번째 자리값 1~4까지의 값만 허용 기능함수
  private static void checkAfterNo(String regNo) {
    sortSex = Integer.parseInt(regNo.substring(7, 8));
    
    while (sortSex <0 | sortSex >4 ) {
      System.out.println("뒷자리를 정확히 입력해 주세요.(1~4 사이) ");
      System.out.print("주민등록번호를 입력해 주세요. ");
      regNo = sc.nextLine();
    }
    printSex(sortSex);
  }
  
//3. 뒷번호 첫번째 자리값을 가지고 1,3 남자 , 2,4 여자 출력 기능함수
  private static void printSex(int sortSex) {
    if (sortSex ==1 | sortSex==3 ) {
      System.out.println("남자");
    } else System.out.println("여자");
  }
  
  
  public static void main(String[] args) {
 
    System.out.print("주민등록번호를 입력해 주세요. ");
    regNo = sc.nextLine();
    checkNo(regNo);
    
    
  }

}
