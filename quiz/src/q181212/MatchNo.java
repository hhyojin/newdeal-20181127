package q181212;

import java.util.Scanner;

public class MatchNo {

  public static void main(String[] args) {
    /* 0~99까지의 난수에서 정해진 임의의 숫자 맞추기

    정해진 수가 77인 경우,
    55라고 입력하면 더 높게
    80을 입력하면 더 낮게 출력되어
    범위를 좁혀 가며 수를 맞춤
    게임 반복을 위해
    Y/N을 묻고 N을 입력하면 종료*/
    String replay = match();
    if(replay.equalsIgnoreCase("Y")) { 
      match();
    } else  System.exit(0);


  }

  private static String match() {
    int rdNp = (int)(Math.random()*99)+1;

    Scanner key = new Scanner(System.in);
    System.out.print("수를 결정했습니다. 맞춰보세요 (0~99) ");
    int keyNo = Integer.parseInt(key.nextLine());

    String answer;

    if(rdNp != keyNo) {
      while(rdNp != keyNo) {
        if (rdNp > keyNo) {
          System.out.print("더 높게");
          keyNo = Integer.parseInt(key.nextLine());
        } else {System.out.print("더 낮게");
        keyNo = Integer.parseInt(key.nextLine());
        }
      }
    } System.out.print("정답~~RESTART? (Y/N)");
      answer = key.nextLine();

    return answer;
  }

}
