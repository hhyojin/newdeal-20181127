package s181214;

import java.util.Scanner;

public class StringQ2 {

  private static String regNo;

  //1. 자리수 체크 (기능)함수 (14 ok)
  private static boolean checkNo(String regNo) {
    boolean check = false;
    check = regNo.length()==14?true:false;
    return check;
  }


  //2. 뒷번호 첫번째 자리값 1~4까지의 값만 허용 기능함수
  private static boolean checkAfterNo(String regNo) {
    /*char sortSex = regNo.charAt(7);*/
    boolean numcheck = false;

    try {int sortSex = Integer.parseInt(regNo.substring(7, 8));
      if (sortSex  > 0 & sortSex <5) {
        numcheck = true;
      }
    }catch (Exception e) {
      
    } 
    return numcheck;
  }

  //3. 뒷번호 첫번째 자리값을 가지고 1,3 남자 , 2,4 여자 출력 기능함수
  private static void printSex(String regNo) {
    char cgen = regNo.replace("-", "").charAt(6);
    // 123456-1234567 -> 1234561234567 > 123456[1]234567 추출> '1'
    switch (cgen) {
      case '1': // break 생략
      case '3':
        System.out.println("남자^^");
        break;
      case '2': // break 생략
      case '4':
        System.out.println("여자^^");
        break;
      default:
        System.out.println("중성");
    }    
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    do {
      System.out.print("주민등록번호를 입력해 주세요. ");
      regNo = sc.nextLine();
    } while (!checkNo(regNo) | !checkAfterNo(regNo));
    // 둘다 true 인경우  > false || false 탈출
    // !true || !true => false || false 탈출    
    printSex(regNo);
  }


}
